package com.zblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zblog.core.dal.mapper.BaseMapper;
import com.zblog.core.dal.mapper.SecondCategoryMapper;

@Service
public class SecondCategoryService extends BaseService {
	@Autowired
	private SecondCategoryMapper secondCategoryMapper;

	@Override
	protected BaseMapper getMapper() {
		return secondCategoryMapper;
	}

}
