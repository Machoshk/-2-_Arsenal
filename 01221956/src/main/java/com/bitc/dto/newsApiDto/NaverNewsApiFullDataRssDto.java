package com.bitc.dto.newsApiDto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="rss")
public class NaverNewsApiFullDataRssDto {
	private NaverNewsApiFulldataDto channel;
	@XmlElement(name="channel")
	public NaverNewsApiFulldataDto getChannel() {
		return channel;
	}

	public void setChannel(NaverNewsApiFulldataDto channel) {
		this.channel = channel;
	}
	
}
