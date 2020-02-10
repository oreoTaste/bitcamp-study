// 서버와 입출력 테스트 - character stream

package com.eomcs.net.ex03;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client0150 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    try (Socket socket = new Socket("localhost", 8888);
        PrintStream out = new PrintStream(socket.getOutputStream());
        Scanner in = new Scanner(socket.getInputStream())) {

      System.out.println("서버와 연결되었음!");

      System.out.println("서버에 메시지(100) 발사 대기");
      scanner.nextLine();
      out.println("ABC가각간");
      //out.flush();
      System.out.println("서버에 데이터를 보냈음!");

      System.out.println("서버에 메시지 수신 대기");
      String response = in.nextLine();
      System.out.println(response);

    } catch (Exception e) {
      e.printStackTrace();
    }
    scanner.close();
  }
}


