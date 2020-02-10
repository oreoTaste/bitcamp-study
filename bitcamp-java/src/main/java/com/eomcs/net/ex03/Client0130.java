// 서버와 입출력 테스트 : Data 주고받기
// flush 안써도됨
package com.eomcs.net.ex03;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client0130 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    try (Socket socket = new Socket("localhost", 8888);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream())) {

      System.out.println("서버와 연결되었음!");
      System.out.println("서버에 메시지(100) 발사 대기");
      scanner.nextLine();
      
      // 서버에 int값 보내기
      out.writeInt(1567891234);
      System.out.println("서버에 데이터를 보냈음!");

      System.out.println("서버에 메시지 수신 대기");
      int value = in.readInt();
      System.out.println(value);

    } catch (Exception e) {
      e.printStackTrace();
    }
    scanner.close();
  }
}


