package com.app.model.response;

public class PostResponse {
	private long id;
	private String date;
	private String title;
	private String content;
	private String author;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	public void subStr() {
		if(this.title.length() > 40) {
			this.title = this.title.substring(0, 39) +"...";
		}
	}

}
