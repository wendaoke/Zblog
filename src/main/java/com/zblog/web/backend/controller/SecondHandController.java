package com.zblog.web.backend.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.http.HttpServletResponse;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.zblog.biz.SecondCategoryManager;
import com.zblog.biz.SecondHandCategoryManager;
import com.zblog.biz.SecondHandManager;
import com.zblog.biz.SecondPictureManager;
import com.zblog.core.dal.entity.SecondHand;
import com.zblog.core.dal.entity.SecondPicture;
import com.zblog.core.plugin.MapContainer;
import com.zblog.core.plugin.PageModel;
import com.zblog.core.util.IdGenerator;
import com.zblog.core.util.StringUtils;
import com.zblog.service.SecondHandService;
import com.zblog.web.backend.form.validator.SecondHandFormValidator;
import com.zblog.web.backend.model.FileMeta;
import com.zblog.web.support.WebContextFactory;

@Controller("adminSecondHandController")
@RequestMapping("/backend/secondhand")
@RequiresRoles(value = { "admin", "editor" }, logical = Logical.OR)
public class SecondHandController {
	@Autowired
	private SecondHandService secondHandService;
	@Autowired
	private SecondHandManager secondHandManager;
	@Autowired	
	private SecondPictureManager secondPictureManager;
	@Autowired	
	private SecondCategoryManager secondCategoryManager;
	@Autowired		
	private SecondHandCategoryManager secondHandCategoryManager;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String data(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
		PageModel<SecondHand> pageModel = secondHandManager.listHistory(page, 15);
		model.addAttribute("page", pageModel);
		return "backend/secondhand/list";
	}

	@ResponseBody
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Object insert(SecondHand hand) {
		MapContainer form = SecondHandFormValidator.validateInsert(hand);
		if (!form.isEmpty()) {
			return form.put("success", false);
		}

		hand.setId(IdGenerator.uuid19());
		hand.setCreateTime(new Date());
		hand.setCreator(WebContextFactory.get().getUser().getId());
		hand.setLastUpdate(hand.getCreateTime());
		boolean result = secondHandManager.insertSecondHand(hand);
		if(result){
			form.put("success", true);
			form.put("msg", "发布成功！");
		}else{
			form.put("success", false);
			form.put("msg", "发布失败，请联系管理员！");			
		}
		return form;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(String secondhand,Model model) {
		SecondHand secondHand = new SecondHand();
		if (!StringUtils.isBlank(secondhand)) {
			secondHand = secondHandManager.loadById(secondhand);
			model.addAttribute("existedpiclst", secondPictureManager.loadBySecondHandId(secondhand));

		}else {
			secondHand.setId(IdGenerator.uuid19());
		}
		model.addAttribute("secondHand", secondHand);
		model.addAttribute("categorys", secondCategoryManager.list());	
		return "backend/secondhand/edit";
	}

	@ResponseBody
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	public Object remove(@PathVariable String id) {
		return new MapContainer("success", secondHandService.deleteById(id));
	}

	LinkedList<FileMeta> files = new LinkedList<FileMeta>();
	FileMeta fileMeta = null;

	/***************************************************
	 * URL: /rest/controller/upload upload(): receives files
	 * 
	 * @param request
	 *            : MultipartHttpServletRequest auto passed
	 * @param response
	 *            : HttpServletResponse auto passed
	 * @return LinkedList<FileMeta> as json format
	 ****************************************************/
	@RequestMapping(value = "/upload/{secondhand}", method = RequestMethod.POST)
	public @ResponseBody
	LinkedList<FileMeta> upload(@PathVariable String secondhand, MultipartHttpServletRequest request, HttpServletResponse response) {

		// 1. build an iterator
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = null;

		// 2. get each file
		while (itr.hasNext()) {

			// 2.1 get next MultipartFile
			mpf = request.getFile(itr.next());

			// 2.2 if files > 10 remove the first from the list
			if (files.size() >= 10)
				files.pop();

			// 2.3 create new fileMeta
			fileMeta = new FileMeta();
			fileMeta.setFileName(mpf.getOriginalFilename());
			fileMeta.setFileSize(mpf.getSize() / 1024 + " Kb");
			fileMeta.setFileType(mpf.getContentType());

			try {
				fileMeta.setBytes(mpf.getBytes());
				File dir = new File("E:/tmp/" + secondhand);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				fileMeta.setFilePath(dir.getAbsolutePath());
				FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(dir +File.separator+ mpf.getOriginalFilename()));
				fileMeta.setSmallFileName(File.separator+"small_"+mpf.getOriginalFilename());
				Thumbnails.of(dir + File.separator+mpf.getOriginalFilename()).size(80, 80).toFile(dir + File.separator+fileMeta.getSmallFileName());  
				SecondPicture secondPicture = new SecondPicture();
				secondPicture.setId(IdGenerator.uuid19());
				secondPicture.setSecondHand(secondhand);
				secondPicture.setName(mpf.getOriginalFilename());
				secondPictureManager.insert(secondPicture);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 2.4 add to files
			files.add(fileMeta);
		}
		// result will be like this
		// [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]
		return files;
	}

	/***************************************************
	 * URL: /rest/controller/get/{value} get(): get file as an attachment
	 * 
	 * @param response
	 *            : passed by the server
	 * @param value
	 *            : value from the URL
	 * @return void
	 ****************************************************/
	@RequestMapping(value = "/getimg/{value}", method = RequestMethod.GET)
	public void getImg(HttpServletResponse response, @PathVariable String value) {
		FileMeta getFile = files.get(Integer.parseInt(value));
		try {
			response.setContentType(getFile.getFileType());
			response.setHeader("Content-disposition", "attachment; filename=\"" + getFile.getFileName() + "\"");
			FileCopyUtils.copy(getFile.getBytes(), response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/delimg/{id}", method = RequestMethod.GET)
	public void delImg(HttpServletResponse response, @PathVariable String id) {
		FileMeta getFile = files.get(Integer.parseInt(id));
		try {
			response.setContentType(getFile.getFileType());
			response.setHeader("Content-disposition", "attachment; filename=\"" + getFile.getFileName() + "\"");
			FileCopyUtils.copy(getFile.getBytes(), response.getOutputStream());
			
			secondPictureManager.deleteById(id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
