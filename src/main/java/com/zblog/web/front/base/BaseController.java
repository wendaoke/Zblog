package com.zblog.web.front.base;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.zblog.core.util.BrowserUtils;

public abstract class BaseController {
	protected HttpServletRequest request;  
    protected HttpServletResponse response;  
    protected HttpSession session;  
    public final String WEIXIN_PATH = "weixin";
      
    @ModelAttribute  
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){  
        this.request = request;  
        this.response = response;  
        this.session = request.getSession();  
    }
	public String analyzeView(String jsp){
		String userAgent = request.getHeader("User-Agent") ;
		if(BrowserUtils.checkWeixin(userAgent)){
			return WEIXIN_PATH+File.separator+jsp;
		}else{
			return jsp;
		}
	}
}
