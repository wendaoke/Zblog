package com.zblog.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zblog.core.dal.entity.SecondCategory;
import com.zblog.service.SecondCategoryService;

@Component
public class SecondCategoryManager{
  @Autowired
  private SecondCategoryService secondCategoryService;
  
  public List<SecondCategory> list(){
	  return secondCategoryService.list();
  }
  
  
}
