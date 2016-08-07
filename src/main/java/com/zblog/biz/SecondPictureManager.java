package com.zblog.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zblog.core.dal.entity.SecondPicture;
import com.zblog.service.SecondPictureService;

@Component
public class SecondPictureManager{
  @Autowired
  private SecondPictureService secondPictureService;
	public List<SecondPicture> loadBySecondHandId(String secondHand) {
		return secondPictureService.loadBySecondHandId(secondHand);
	}
	
	public int insert(SecondPicture secondPicture) {
		return secondPictureService.insert(secondPicture);
	}
	public int deleteById(String id){
		return secondPictureService.deleteById(id);
	}
}
