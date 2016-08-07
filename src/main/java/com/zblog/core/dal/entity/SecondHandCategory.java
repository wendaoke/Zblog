package com.zblog.core.dal.entity;

public class SecondHandCategory extends BaseEntity{
	private String secondCategory;
	private String secondHand;
	public String getSecondCategory() {
		return secondCategory;
	}
	public String getSecondHand() {
		return secondHand;
	}
	public void setSecondCategory(String secondCategory) {
		this.secondCategory = secondCategory;
	}
	public void setSecondHand(String secondHand) {
		this.secondHand = secondHand;
	}
	
}
