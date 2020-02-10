// 서버와 입출력 테스트 : Data 주고받기
// flush 안써도됨
package com.eomcs.net.ex03;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client0140 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    try (Socket socket = new Socket("localhost", 8888);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream())) {

      
      System.out.println("서버와 연결되었음!");
      System.out.println("서버에 데이터 발사 대기");
      scanner.nextLine();
      
      // 서버에 int값 보내기
      out.writeInt(1567891234);
      out.writeByte(100);
      out.writeFloat(3.14f);
      out.writeUTF("ABC가각간");
      System.out.println("서버에 데이터를 보냈음!");

      System.out.println("서버에 메시지 수신 대기");
      int value = in.readInt();
      byte value2 = in.readByte();
      float value3 = in.readFloat();
      String value4 = in.readUTF();
      System.out.printf("%d, %d, %f, %s\n",value, value2, value3, value4);

    } catch (Exception e) {
      e.printStackTrace();
    }
    scanner.close();
  }
}


