package com.zblog.web.backend.form.validator;

import com.zblog.core.dal.entity.SecondCategory;
import com.zblog.core.plugin.MapContainer;
import com.zblog.core.util.StringUtils;

public class SecondCategoryFormValidator{

  public static MapContainer validateInsert(SecondCategory category){
    MapContainer form = new MapContainer();
    if(StringUtils.isBlank(category.getName())){
      form.put("msg", "分类名称不能为空");
    }

    return form;
  }

}
