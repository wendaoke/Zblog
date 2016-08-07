package com.zblog.core.dal.mapper;

import java.util.List;

import com.zblog.core.dal.entity.SecondPicture;

public interface SecondPictureMapper extends BaseMapper{

	public List<SecondPicture> loadBySecondHandId(String secondHand);
   
}
