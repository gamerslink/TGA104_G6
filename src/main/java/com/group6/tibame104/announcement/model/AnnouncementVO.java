package com.group6.tibame104.announcement.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class AnnouncementVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer announcementSerialID;
	private Integer administratorID;
	private String announcementTitle;
	private String announcementContent;
	private Date startDate;
	private Date endDate;
	private Timestamp updateTime;
	private Boolean offLoadStatus;
	private Boolean showStatus;
	
	public Integer getAnnouncementSerialID() {
		return announcementSerialID;
	}
	public void setAnnouncementSerialID(Integer announcementSerialID) {
		this.announcementSerialID = announcementSerialID;
	}
	public Integer getAdministratorID() {
		return administratorID;
	}
	public void setAdministratorID(Integer administratorID) {
		this.administratorID = administratorID;
	}
	public String getAnnouncementTitle() {
		return announcementTitle;
	}
	public void setAnnouncementTitle(String announcementTitle) {
		this.announcementTitle = announcementTitle;
	}
	public String getAnnouncementContent() {
		return announcementContent;
	}
	public void setAnnouncementContent(String announcementContent) {
		this.announcementContent = announcementContent;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public Boolean getOffLoadStatus() {
		return offLoadStatus;
	}
	public void setOffLoadStatus(Boolean offLoadStatus) {
		this.offLoadStatus = offLoadStatus;
	}
	public Boolean getShowStatus() {
		return showStatus;
	}
	public void setShowStatus(Boolean showStatus) {
		this.showStatus = showStatus;
	}
	
	

}
