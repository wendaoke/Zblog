package com.zblog.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zblog.core.dal.entity.SecondHand;
import com.zblog.core.plugin.PageModel;
import com.zblog.service.SecondHandService;

@Component
public class SecondHandManager{
  @Autowired
  private SecondHandService secondHandService;
	public PageModel<SecondHand> listHistory(int pageIndex, int pageSize) {
		return secondHandService.listHistory(pageIndex, pageSize);
	}
	
	public SecondHand loadById(String id){
		return secondHandService.loadById(id);
	}

}
