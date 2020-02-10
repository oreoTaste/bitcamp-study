// stateful 방식 - 계산기 서버 만들기
package com.eomcs.net.ex04.stateful;

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

      loop1: while (true) {
        int a = in.readInt();
        String op = in.readUTF();
        int b = in.readInt();
        int result = 0;

        loop2 : switch (op) {
          case "+": result = a + b; break loop2;
          case "-": result = a - b; break loop2;
          case "*": result = a * b; break loop2;
          case "/": result = a / b; break loop2;
          case "quit": break loop1;
        }

        out.printf("%d %s %d = %d\n", a, op, b, result);
      }
    out.println("quit");
    }
  }
}





