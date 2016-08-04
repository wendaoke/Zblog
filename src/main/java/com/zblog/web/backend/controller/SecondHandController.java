package com.zblog.web.backend.controller;

import java.util.Date;
import java.util.List;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zblog.biz.SecondHandManager;
import com.zblog.core.dal.entity.SecondHand;
import com.zblog.core.plugin.MapContainer;
import com.zblog.core.plugin.PageModel;
import com.zblog.core.util.IdGenerator;
import com.zblog.core.util.StringUtils;
import com.zblog.service.SecondHandService;
import com.zblog.service.vo.PostVO;
import com.zblog.web.backend.form.validator.SecondHandFormValidator;
import com.zblog.web.support.WebContextFactory;

@Controller("adminSecondHandController")
@RequestMapping("/backend/secondhand")
@RequiresRoles(value = { "admin", "editor" }, logical = Logical.OR)
public class SecondHandController{
  @Autowired
  private SecondHandService secondHandService;
  @Autowired
  private SecondHandManager secondHandManager;


  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String data(@RequestParam(value = "page", defaultValue = "1") int page,Model model){
	  PageModel<SecondHand> pageModel  = secondHandManager.listHistory(page, 15);
	    model.addAttribute("page", pageModel);
    return "backend/secondhand/list";
  }

  @ResponseBody
  @RequestMapping(value = "/insert",method = RequestMethod.POST)
  public Object insert(SecondHand hand){
    MapContainer form = SecondHandFormValidator.validateInsert(hand);
    if(!form.isEmpty()){
      return form.put("success", false);
    }

    hand.setId(IdGenerator.uuid19());
    hand.setCreateTime(new Date());
    hand.setCreator(WebContextFactory.get().getUser().getId());
    hand.setLastUpdate(hand.getCreateTime());
    return new MapContainer("success", secondHandService.insert(hand));
  }

  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(String pid, Model model){
    if(!StringUtils.isBlank(pid)){
      model.addAttribute("secondHand", secondHandManager.loadById(pid));
    }

    return "backend/secondhand/edit";
  }

  
  @ResponseBody
  @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
  public Object remove(@PathVariable String id){
    return new MapContainer("success",  secondHandService.deleteById(id));
  }

}
