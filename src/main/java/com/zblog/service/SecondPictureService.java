package com.zblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zblog.core.dal.entity.SecondPicture;
import com.zblog.core.dal.mapper.BaseMapper;
import com.zblog.core.dal.mapper.SecondPictureMapper;

@Service
public class SecondPictureService extends BaseService {
	@Autowired
	private SecondPictureMapper secondPictureMapper;

	public List<SecondPicture> loadBySecondHandId(String secondHand) {
		return secondPictureMapper.loadBySecondHandId(secondHand);
	}

	public int insert(SecondPicture secondPicture){
		return secondPictureMapper.insert(secondPicture);
	}
	
	
	@Override
	protected BaseMapper getMapper() {
		return secondPictureMapper;
	}

}
