package com.bitc.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class NaverNewApi {
	// https://developers.naver.com/docs/search/news/


	    public static void main(String[] args) throws IOException {
	    
	        String clientId = "oV5R3DJoU5V5TWS5lEX5";//애플리케이션 클라이언트 아이디값";
	        String clientSecret = "GdvNRhneG7";//애플리케이션 클라이언트 시크릿값";
	        try {
	            String text = URLEncoder.encode("아스날", "UTF-8"); //검색어";
	            String apiURL = "https://openapi.naver.com/v1/search/news.xml?query="+ text + "&display=10&start=1&sort=date"; // 뉴스의 json 결과
	       //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // 블로그의 xml 결과 
	            URL url = new URL(apiURL);
	            HttpURLConnection con = (HttpURLConnection)url.openConnection();
	            con.setRequestMethod("GET");
	            con.setRequestProperty("X-Naver-Client-Id", clientId);
	            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
	            int responseCode = con.getResponseCode();
	            BufferedReader br;
	            if(responseCode==200) { // 정상 호출
	                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	            } else {  // 에러 발생
	                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	            }
	            String inputLine;
	            StringBuffer response = new StringBuffer();
	            while ((inputLine = br.readLine()) != null) {
	                response.append(inputLine);
	            }
	            br.close();
//	            System.out.println(response.toString());
	            String xml=response.toString();
	            //C:\workspace\goods
	            String fileNm="C://workspace//hhhh//네이버뉴스.xml";
	            File file=new File(fileNm);
	            FileWriter fileWrite = new FileWriter(file,false);
	            fileWrite.write(xml);
	            fileWrite.flush();
	            fileWrite.close();
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	    }
	   
	    
	}