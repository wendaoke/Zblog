package com.zblog.core.dal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zblog.core.dal.entity.Category;
import com.zblog.core.plugin.MapContainer;

@SuppressWarnings("unchecked")
public interface SecondHandMapper extends BaseMapper{

  List<MapContainer> list();
   
}
