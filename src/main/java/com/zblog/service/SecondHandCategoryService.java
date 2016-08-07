package com.zblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zblog.core.dal.entity.SecondHandCategory;
import com.zblog.core.dal.mapper.BaseMapper;
import com.zblog.core.dal.mapper.SecondHandCategoryMapper;

@Service
public class SecondHandCategoryService extends BaseService {
	@Autowired
	private SecondHandCategoryMapper secondHandCategoryMapper;

	public List<SecondHandCategory> loadBySecondHand(String secondhand) {
		return secondHandCategoryMapper.loadBySecondHand(secondhand);
	}

	  public int deleteBySecondHand(String secondhand){
		  return secondHandCategoryMapper.deleteBySecondHand(secondhand);
	  }
	@Override
	protected BaseMapper getMapper() {
		return secondHandCategoryMapper;
	}

}
