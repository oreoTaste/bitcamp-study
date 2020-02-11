// stateless 방식 - 계산기 클라이언트 만들기
package com.eomcs.net.ex04.stateless3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class CalcClient {
  public static void main(String[] args) throws Exception {
    Scanner keyScan = new Scanner(System.in);
    long clientId = 0;
    while (true) {

      System.out.print("연산자? ");
      String op = keyScan.nextLine();

      System.out.print("값? ");
      int value = Integer.parseInt(keyScan.nextLine());


      try(
          Socket socket = new Socket("localhost", 8888);
          DataOutputStream out = new DataOutputStream(socket.getOutputStream());
          DataInputStream in = new DataInputStream(socket.getInputStream())){

        out.writeLong(clientId);
        out.writeUTF(op);
        out.writeInt(value);
        out.flush();

        clientId = in.readLong();
        int result = in.readInt();
        System.out.printf("계산결과 : %d\n",result);

        System.out.print("계속하시겠습니까?(Y/n) ");
      }
      String contin = keyScan.nextLine();
      if(contin.equalsIgnoreCase("n")) {
        break;
      }
      // keyScan.close();
    }
  }
}





