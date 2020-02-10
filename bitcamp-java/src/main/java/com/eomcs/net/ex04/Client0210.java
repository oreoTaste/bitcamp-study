// 통신방식 - Stateful 방식

package com.eomcs.net.ex04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client0210 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    try (Socket socket = new Socket("192.168.1.10", 8888);
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        BufferedReader in = new BufferedReader(
            new InputStreamReader(socket.getInputStream()))) {

      String name;
      do {
        System.out.println("서버와 연결되었음!");

        System.out.print("이름 ? ");
        name = scanner.nextLine();
        out.println(name);
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
        String response = in.readLine();
        System.out.println(response);
      } while(!name.equalsIgnoreCase("good bye"));

    } catch (Exception e) {
      e.printStackTrace();
    }
    scanner.close();
  }
}


