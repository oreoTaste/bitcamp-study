package com.eomcs.httpcomponents.client;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;

// 크롤링에 활용가능
public class Exam0130 {
  public static void main(String[] args) throws Exception {

    // try-with-resources 문법 적용
    try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
      HttpGet httpGet = new HttpGet("http://daum.net");
      try (CloseableHttpResponse response1 = httpclient.execute(httpGet)) {
        System.out.println(response1.getCode() + " " + response1.getReasonPhrase());
        HttpEntity entity1 = response1.getEntity();
        System.out.println(EntityUtils.toString(entity1));
      }
    }
  }
}
