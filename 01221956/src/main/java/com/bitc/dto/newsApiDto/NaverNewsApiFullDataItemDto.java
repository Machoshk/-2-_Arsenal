package com.bitc.dto.newsApiDto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="item")
public class NaverNewsApiFullDataItemDto {
	private String title;
	private String originallink;
	private String link;
	private String description;
	
	
	@XmlElement(name="title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@XmlElement(name="originallink")
	public String getOriginallink() {
		return originallink;
	}
	public void setOriginallink(String originallink) {
		this.originallink = originallink;
	}
	@XmlElement(name="link")
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	@XmlElement(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
