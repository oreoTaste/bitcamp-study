package com.eomcs.httpcomponents.client;

import org.apache.hc.client5.http.config.Request;

// 크롤링에 활용가능2
public class Exam0140 {
  public static void main(String[] args) throws Exception {

//    httpclient-fluent 라이브러리추가
    Content result = Request.get("http://targethost/homepage").execute().returnContent();
    System.out.println(result.toString());
    
  }
}
