// stateful 방식 - 계산기 서버 만들기 2 : Stateful 방식의 이점 활용
package com.eomcs.net.ex04.stateful2;

import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CalcServer {
  public static void main(String[] args) throws Exception {
    System.out.println("서버 실행 중...");

    ServerSocket ss = new ServerSocket(8888);

    while (true) {
      Socket socket = ss.accept();
      // stateful을 사용할때 이점
      // 연결되어 있는 동안 클라이언트의 작업결과를 계속 유지할 수 있다.
      try {
        processRequest(socket);
      } catch (Exception e) {
        System.out.println("클라이언트 요청 처리 중 오류 발생!");
        System.out.println("다음 클라이언트의 요청을 처리합니다.");
      }
    }
    //ss.close();
  }

  static void processRequest(Socket socket) throws Exception {
    try (
        Socket socket2 = socket;
        DataInputStream in = new DataInputStream(socket.getInputStream());
        PrintStream out = new PrintStream(socket.getOutputStream());
        ) {

      // 계산 결과를 유지할 변수 생성
      int result = 0;
      
      loop1: while (true) {
        String op = in.readUTF();
        int a = in.readInt();

        loop2 : switch (op) {
          case "+": result += a; break loop2;
          case "-": result -= a; break loop2;
          case "*": result *= a; break loop2;
          case "/": result /= a; break loop2;
          case "quit": break loop1;
        }

        out.printf("계산 결과 : %d\n", result);
      }
    out.println("quit");
    }
  }
}





