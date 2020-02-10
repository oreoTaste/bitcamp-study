// 클라이언트와 입출력 테스트 - character stream
package com.eomcs.net.ex03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server0210 {
  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);
    try (ServerSocket serverSocket = new ServerSocket(8888)) {

      System.out.println("클라이언트의 연결을 기다리고 있음.");

      try (Socket socket = serverSocket.accept();
          BufferedReader in = new BufferedReader(
              new InputStreamReader(socket.getInputStream()));
          PrintWriter out = new PrintWriter(socket.getOutputStream())) {

        System.out.println("대기열에서 클라이언트 정보를 꺼내 소켓을 생성하였음.");
        System.out.println("클라이언트와 통신할 입출력 스트림이 준비되었음.");

        keyboard.nextLine(); // 사용자가 콘솔창에서 엔터를 칠 때까지 리턴하지 않는다.

        System.out.print("클라이언트가 보낸 String 한줄을 기다리는 중!");
        String request = in.readLine(); // blocking 모드로 작동한다.
        System.out.println(request);

        System.out.print("데이터를 보내기 전에 잠깐!");
        keyboard.nextLine();

        out.println(request);
        System.out.println("클라인트에게 데이터를 보냈음.");
        
        System.out.println("잠시멈춤");
        keyboard.nextLine();

      }
      System.out.println("클라이언트와의 연결을 끊었음.");

    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("서버 종료!");
  }

}

