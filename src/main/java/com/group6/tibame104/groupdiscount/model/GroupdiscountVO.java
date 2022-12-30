package com.group6.tibame104.groupdiscount.model;

public class GroupdiscountVO implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer groupBuyID;
	private Integer groupBuyProductOrderTotal;
	private Double groupBuyCount;
	private Integer countTableID;

	@Override
	public String toString() {
		return "groupdiscount [groupBuyID=" + groupBuyID + ", groupBuyProductOrderTotal=" + groupBuyProductOrderTotal
				+ ", groupBuyCount=" + groupBuyCount + ", countTableID=" + countTableID + "]";
	}

	public Integer getCountTableID() {
		return countTableID;
	}

	public void setCountTableID(Integer countTableID) {
		this.countTableID = countTableID;
	}

	public Integer getGroupBuyID() {
		return groupBuyID;
	}

	public void setGroupBuyID(Integer groupBuyID) {
		this.groupBuyID = groupBuyID;
	}

	public Integer getGroupBuyProductOrderTotal() {
		return groupBuyProductOrderTotal;
	}

	public void setGroupBuyProductOrderTotal(Integer groupBuyProductOrderTotal) {
		this.groupBuyProductOrderTotal = groupBuyProductOrderTotal;
	}

	public Double getGroupBuyCount() {
		return groupBuyCount;
	}

	public void setGroupBuyCount(Double groupBuyCount) {
		this.groupBuyCount = groupBuyCount;
	}
}
