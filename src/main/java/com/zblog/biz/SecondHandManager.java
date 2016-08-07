package com.zblog.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zblog.core.dal.entity.SecondHand;
import com.zblog.core.dal.entity.SecondHandCategory;
import com.zblog.core.plugin.PageModel;
import com.zblog.core.util.IdGenerator;
import com.zblog.service.SecondHandCategoryService;
import com.zblog.service.SecondHandService;

@Component
public class SecondHandManager {
	@Autowired
	private SecondHandService secondHandService;
	@Autowired
	private SecondHandCategoryService secondHandCategoryService;

	public PageModel<SecondHand> listHistory(int pageIndex, int pageSize) {
		return secondHandService.listHistory(pageIndex, pageSize);
	}

	public SecondHand loadById(String id) {
		return secondHandService.loadById(id);
	}

	public boolean insertSecondHand(SecondHand secondHand) {
		secondHandService.insert(secondHand);
		secondHandCategoryService.deleteBySecondHand(secondHand.getId());
		String[] categorylst = secondHand.getCategorys().split(",");
		for (int i = 0; i < categorylst.length; i++) {
			SecondHandCategory secondHandCategory = new SecondHandCategory();
			secondHandCategory.setId(IdGenerator.uuid19());
			secondHandCategory.setSecondHand(secondHand.getId());
			secondHandCategory.setSecondCategory(categorylst[i]);
			secondHandCategoryService.insert(secondHandCategory);
		}
		return true;
	}
}
