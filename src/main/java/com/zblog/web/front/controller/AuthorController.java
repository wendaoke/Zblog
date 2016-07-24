package com.zblog.web.front.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zblog.biz.CommentManager;
import com.zblog.biz.PostManager;
import com.zblog.biz.VisitStatManager;
import com.zblog.core.WebConstants;
import com.zblog.core.dal.entity.User;
import com.zblog.core.util.CookieUtil;
import com.zblog.core.util.StringUtils;
import com.zblog.service.PostService;
import com.zblog.service.UserService;
import com.zblog.service.vo.PostVO;

@Controller
@RequestMapping("/authors")
public class AuthorController {
	@Autowired
	private PostManager postManager;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/{authorid}", method = RequestMethod.GET)
	public String post(@PathVariable("authorid") String authorid, @RequestParam(value = "page", defaultValue = "1") int page, HttpServletRequest request, Model model) {
		model.addAttribute("page", postManager.listPost(page, 10, authorid));
		if (!StringUtils.isBlank(authorid)) {
			User user = userService.loadById(authorid);
			model.addAttribute("author", user);
			model.addAttribute(WebConstants.PRE_TITLE_KEY, user.getRealName());
		}

		return "index";
	}

}
