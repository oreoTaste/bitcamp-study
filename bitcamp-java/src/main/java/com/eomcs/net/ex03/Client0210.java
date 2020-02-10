// 서버와 입출력 테스트 - character stream

package com.eomcs.net.ex03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client0210 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    try (Socket socket = new Socket("localhost", 8888);
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        BufferedReader in = new BufferedReader(
            new InputStreamReader(socket.getInputStream()))) {

      System.out.println("서버와 연결되었음!");

      System.out.println("서버에 메시지(100) 발사 대기");
      scanner.nextLine();
      out.println("ABC가각간");
      /*
      out.write("A");
      out.write("B");
      out.write("C");
      out.write("가");
      out.write("각");
      out.write("간");
      out.write("\n");
      */
      out.flush();
      // character stream 클래스의 경우
      // 출력데이터를 내부 버퍼에 보관하고 있다가
      // flush 가 호출되면 비로서 출력을 수행한다.
      System.out.println("서버에 데이터를 보냈음!");

      System.out.println("서버에 메시지 수신 대기");
      String response = in.readLine();
      System.out.println(response);

    } catch (Exception e) {
      e.printStackTrace();
    }
    scanner.close();
  }
}


