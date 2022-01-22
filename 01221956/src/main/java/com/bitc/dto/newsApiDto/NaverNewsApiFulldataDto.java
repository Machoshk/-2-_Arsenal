package com.bitc.dto.newsApiDto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "channel")
public class NaverNewsApiFulldataDto {
	private String title;
	private String link;
	private String description;
	private String display;
	private int total;
	private int start;
	private List<NaverNewsApiFullDataItemDto> item;
	@XmlElement(name="total")
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	@XmlElement(name="start")
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	@XmlElement(name = "title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@XmlElement(name = "link")
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@XmlElement(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@XmlElement(name = "display")
	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	@XmlElement(name = "item")
	public List<NaverNewsApiFullDataItemDto> getItem() {
		return item;
	}

	public void setItem(List<NaverNewsApiFullDataItemDto> item) {
		this.item = item;
	}

}
