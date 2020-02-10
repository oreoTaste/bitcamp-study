// 통신방식 - Stateless 방식

// stateless

// => 서버에 작업을 요청할 때 연결하고, 서버로부터 응답을 받으면 연결을 끊는다.
// => 즉 요청응답을 한번만 수행한다.

// => 단점:
//    매번 요청할 때마다 서버에 연결해야 하기 때문에 실행 시간이 많이 걸린다.
//    실행시간 = 연결하는데 걸린 시간 + 데이터 전송 시간 + 서버 처리 시간 + 데이터 수신 시간
//    특히 서버에 연결하는데 시간이 많이 걸리기 때문에 실행 시간이 많이 소요된다.
// => 장점:
//    서버에 계속 연결된 상태가 아니기 때문에 서버 입장에서는 메모리를 많이 사용하지 않는다.
//    왜?
//    클라이언트와 연결을 계속 유지하지 않기 때문에 작업을 처리하는 동안만 연결정보를 유지한다.
//    그래서 같은 메모리라도 connectionful 방식 보다는
//    더 많은 클라이언트의 요청을 처리할 수 있다.

// => 대표적인 예:
//    HTTP 통신 - 웹브라우저가 서버에 연결한 후 요청을 하고 서버가 응답한 후 연결을 끊는다.
//    메신저 - 메신저 서버에 연결하고 메시지 전송 후 연결 끊는다.
//    메일 전송 - 메일서버에 데이터 전송 후 연결 끊는다.
package com.eomcs.net.ex04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server0210 {
  public static void main(String[] args) {
    try (Scanner keyboard = new Scanner(System.in);
        ServerSocket serverSocket = new ServerSocket(8888)) {

      System.out.println("서버 실행!");

      while(true) {
        try (Socket socket = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream())) {

          System.out.println("클라이언트가 연결되었음!");

          String name = in.readLine();
          if (name.equalsIgnoreCase("quit")) {
            out.println("Goodbye!");
            out.flush();
            break;
          }
          out.printf("%s 님 반갑습니다!\n", name);
          out.flush();
          out.close();
          System.out.println("클라이언트와 연결 끊음");
        }
      }
      System.out.println("클라이언트와의 연결을 끊었음.");

    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("서버 종료!");
  }

}

