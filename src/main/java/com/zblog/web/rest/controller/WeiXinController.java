package com.zblog.web.rest.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/weixin")
public class WeiXinController {
	private static final Logger LOGGER = Logger.getLogger(WeiXinController.class);

	@RequestMapping(value = "/validate", method = RequestMethod.GET)
	public String validate(HttpServletRequest request, HttpServletResponse response, String signature, String timestamp, String nonce, String echostr,Model model) {
		LOGGER.info("signature:" + signature);
		LOGGER.info("timestamp:" + timestamp);
		LOGGER.info("nonce:" + nonce);
		LOGGER.info("echostr:" + echostr);
		model.addAttribute("echostr", echostr);
		return "weixin/index";
	}
}
