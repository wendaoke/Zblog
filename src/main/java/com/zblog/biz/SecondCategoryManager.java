package com.zblog.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zblog.service.SecondCategoryService;

@Component
public class SecondCategoryManager{
  @Autowired
  private SecondCategoryService secondCategoryService;

  

}
