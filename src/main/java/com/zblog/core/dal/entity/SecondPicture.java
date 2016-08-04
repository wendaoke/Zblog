package com.zblog.core.dal.entity;

public class SecondPicture {
	  private String id;
	  private String secondHand;
	  private String name;
	  private int order;
	public String getId() {
		return id;
	}
	public String getSecondHand() {
		return secondHand;
	}
	public String getName() {
		return name;
	}
	public int getOrder() {
		return order;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setSecondHand(String secondHand) {
		this.secondHand = secondHand;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setOrder(int order) {
		this.order = order;
	}
}
