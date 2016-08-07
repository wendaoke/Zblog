package com.zblog.core.dal.entity;

import java.util.List;

public class SecondHand extends BaseEntity {
	private String title;
	private String content;
	private float originalPrice;
	private float presentPrice;
	private String province;
	private String city;
	private String district;
	private List<SecondPicture> piclst;
	private String categorys;
	public List<SecondPicture> getPiclst() {
		return piclst;
	}

	public void setPiclst(List<SecondPicture> piclst) {
		this.piclst = piclst;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public float getOriginalPrice() {
		return originalPrice;
	}

	public float getPresentPrice() {
		return presentPrice;
	}


	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setOriginalPrice(float originalPrice) {
		this.originalPrice = originalPrice;
	}

	public void setPresentPrice(float presentPrice) {
		this.presentPrice = presentPrice;
	}

	public String getProvince() {
		return province;
	}

	public String getCity() {
		return city;
	}

 

	public void setProvince(String province) {
		this.province = province;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCategorys() {
		return categorys;
	}

	public void setCategorys(String categorys) {
		this.categorys = categorys;
	}



 


}
