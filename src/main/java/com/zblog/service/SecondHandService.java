package com.zblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zblog.core.dal.entity.SecondHand;
import com.zblog.core.dal.mapper.BaseMapper;
import com.zblog.core.dal.mapper.SecondHandMapper;
import com.zblog.core.plugin.PageModel;
import com.zblog.core.tag.ShiroFunctions;

@Service
public class SecondHandService extends BaseService {
	@Autowired
	private SecondHandMapper secondHandMapper;

	public PageModel<SecondHand> listHistory(int pageIndex, int pageSize) {
		PageModel<SecondHand> page = new PageModel<>(pageIndex, pageSize);
		if (ShiroFunctions.isAuthenticated() && !ShiroFunctions.hasRole("admin")) {
			String username = (String) ShiroFunctions.getPrincipals().getPrimaryPrincipal();
			page.insertQuery("creator", username);
		}
		super.list(page);
		return page;
	}

	
	@Override
	protected BaseMapper getMapper() {
		return secondHandMapper;
	}

}
