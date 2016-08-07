package com.zblog.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zblog.core.dal.entity.SecondHandCategory;
import com.zblog.core.util.IdGenerator;
import com.zblog.service.SecondHandCategoryService;
@Component
public class SecondHandCategoryManager {
	  @Autowired
	private SecondHandCategoryService secondHandCategoryService;
	 
	  public boolean insertSecondHandCategory(String secondhand,String categorylst){
		  secondHandCategoryService.deleteBySecondHand(secondhand);
		  String[] categorys = categorylst.split(",");
		  for(int i =0;i<categorys.length;i++){
			  SecondHandCategory secondHandCategory = new SecondHandCategory();
			  secondHandCategory.setId(IdGenerator.uuid19());
			  secondHandCategory.setSecondHand(secondhand);
			  secondHandCategory.setSecondCategory(categorys[i]);
			  secondHandCategoryService.insert(secondHandCategory);
		  }
		  return true;
	  }
}
