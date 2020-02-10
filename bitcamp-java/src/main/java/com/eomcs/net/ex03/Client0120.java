// 서버와 입출력 테스트 - 바이트 배열 보내고 받기
package com.eomcs.net.ex03;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client0120 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    try (Socket socket = new Socket("localhost", 8888);
        OutputStream out = socket.getOutputStream();
        InputStream in = socket.getInputStream()) {

      System.out.println("서버와 연결되었음!");
      System.out.println("서버에 메시지(100) 발사 대기");
      scanner.nextLine();
      byte[] bytes = new byte[100];
      for(int i = 0; i < 100; i++) {
        bytes[i] = (byte)i;
      }
      
      out.write(bytes);
      //out.flush();
      System.out.println("서버에 데이터를 보냈음!");

      System.out.println("서버에 메시지 수신 대기");
      byte[] buf = new byte[100];
      int size = in.read(buf);
      System.out.printf("바이트 배열의 크기 : " + size);
      
      for(int i = 0; i < size; i++) {
        if(i > 0 && (i %20) == 0) {
          System.out.println();
        }
        System.out.printf("%x ", buf[i]);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    scanner.close();
  }
}


