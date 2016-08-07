package com.zblog.web.backend.form.validator;


import com.zblog.core.dal.entity.SecondHand;
import com.zblog.core.plugin.MapContainer;
import com.zblog.core.util.StringUtils;

public class SecondHandFormValidator {

	public static MapContainer validateInsert(SecondHand hand) {
		MapContainer form = new MapContainer();
		if (StringUtils.isBlank(hand.getTitle())) {
			form.put("msg", "标题不能为空");
		} else if (StringUtils.isBlank(hand.getContent())) {
			form.put("msg", "内容不能为空");
		}else if(StringUtils.isBlank(hand.getProvince())){
			form.put("msg", "省份不能为空");
		}else if(StringUtils.isBlank(hand.getCity())){
			form.put("msg", "城市不能为空");
		}else if(StringUtils.isBlank(hand.getDistrict())){
			form.put("msg", "地区不能为空");
		}
		return form;
	}

}
