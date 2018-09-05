package com.newlecture.webapp.entity;

import java.sql.Date;

public class Question {

	
	private long id;
	private String content;
	private String figure;
	private Date regDate;
	private String regUseId;
	
	public Question() {	
	
	}

	public Question(String content, String figure, String regUseId) {
		
		this.content = content;
		this.figure = figure;
		this.regUseId = regUseId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFigure() {
		return figure;
	}

	public void setFigure(String figure) {
		this.figure = figure;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getRegUseId() {
		return regUseId;
	}

	public void setRegUseId(String regUseId) {
		this.regUseId = regUseId;
	}
	
}
