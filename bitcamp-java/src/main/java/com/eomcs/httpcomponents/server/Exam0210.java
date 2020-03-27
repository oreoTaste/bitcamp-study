// Apache HttpClient 사용법 : 동적 콘텐트를 출력하는 웹서버 만들기
package com.eomcs.httpcomponents.server;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.ConnectionClosedException;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ExceptionListener;
import org.apache.hc.core5.http.HttpConnection;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.impl.bootstrap.HttpServer;
import org.apache.hc.core5.http.impl.bootstrap.ServerBootstrap;
import org.apache.hc.core5.http.io.HttpRequestHandler;
import org.apache.hc.core5.http.io.SocketConfig;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.apache.hc.core5.io.CloseMode;
import org.apache.hc.core5.util.TimeValue;

public class Exam0210 {
  public static void main(final String[] args) throws Exception {

    final SocketConfig socketConfig = SocketConfig.custom()
        .setSoTimeout(3, TimeUnit.SECONDS)
        .setTcpNoDelay(true)
        .build();

    final HttpServer server = ServerBootstrap.bootstrap()
        .setListenerPort(9999) // 웹서버 포트 번호 설정
        .setSocketConfig(socketConfig) // 기본소켓 동작 설정
        .setSslContext(null) // SSL 설정
        .setExceptionListener(new ExceptionListener() { // 예외 처리자 설정

          @Override
          public void onError(final Exception ex) {
            ex.printStackTrace();
          }

          @Override
          public void onError(final HttpConnection conn, final Exception ex) {
            if (ex instanceof SocketTimeoutException) {
              System.err.println("Connection timed out");
            } else if (ex instanceof ConnectionClosedException) {
              System.err.println(ex.getMessage());
            } else {
              ex.printStackTrace();
            }
          }

        })
        .register("*", new MyRequestHandler()) // 요청처리자 설정
        .create();

    server.start();

    Runtime.getRuntime().addShutdownHook(new Thread() { //웹서버를 종료시키는 스레드를 등록
      @Override
      public void run() {
        server.close(CloseMode.GRACEFUL);
      }
    });
    System.out.println("서버시작!");
    server.awaitTermination(TimeValue.MAX_VALUE);

  }

  static class MyRequestHandler implements HttpRequestHandler  {
    @Override
    public void handle(final ClassicHttpRequest request, // 클라이언트 요청처리
        final ClassicHttpResponse response, // 클라이언트 응답처리
        final HttpContext context // HTTP 설정도구
        ) throws HttpException, IOException {

      response.setCode(HttpStatus.SC_OK);
      final StringEntity body = new StringEntity(
          "<html><body><h1>안녕하세요<h1></body></html>",
          ContentType.create("text/html", Charset.forName("UTF-8")));
      response.setEntity(body);
    }
  }


}





