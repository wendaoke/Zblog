package com.zblog.core.dal.mapper;

import java.util.List;

import com.zblog.core.dal.entity.SecondHandCategory;
import com.zblog.core.plugin.MapContainer;

@SuppressWarnings("unchecked")
public interface SecondHandCategoryMapper extends BaseMapper{

  List<MapContainer> list();
  public List<SecondHandCategory> loadBySecondHand(String secondHand);
  public int deleteBySecondHand(String secondhand);
}
