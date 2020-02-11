// stateful 방식 - 다중클라이언트의 요청처리시 문제점과 해결책
package com.eomcs.net.ex04.stateful3;

import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CalcServer {
  
  static class RequestHandler extends Thread {
    Socket socket;
    
    RequestHandler(Socket socket) {
      this.socket = socket;
    }
    
    @Override
    public void run() {
      try {
        processRequest(socket);
        super.run();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
  
  public static void main(String[] args) throws Exception {
    System.out.println("서버 실행 중...");

    ServerSocket ss = new ServerSocket(8888);

    while (true) {
      try {
      Socket socket = ss.accept();
      System.out.printf("클라이언트(%s, %s) 연결됨\n",
          socket.getInetAddress(),
          socket.getRemoteSocketAddress().toString().substring(
              socket.getRemoteSocketAddress().toString().indexOf(":")+1));
      // stateful을 사용할때 이점
      // 연결되어 있는 동안 클라이언트의 작업결과를 계속 유지할 수 있다.
      
      RequestHandler requestHandler = new RequestHandler(socket);
      requestHandler.start();
      
      
      } catch (Exception e) {
        System.out.println("클라이언트 요청 처리 중 오류 발생!");
        System.out.println("다음 클라이언트의 요청을 처리합니다.");
      }
      // 사용중인 클라이언트가 연결을 끊기전까지는
      // 대기중인 클라이언트의 요청처리 불가.
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





