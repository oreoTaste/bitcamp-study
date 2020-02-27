package com.eomcs.lms;

import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;

public class ServerAppTest {
  @SuppressWarnings("unchecked")
  public static void testBoardList() {
    String serverAddr = null;
    int port = 0;
    
    Scanner keyScan = new Scanner(System.in);
    
    try{
      System.out.print("서버? ");
      serverAddr = keyScan.nextLine();
      
      System.out.print("포트? ");
      port = Integer.parseInt(keyScan.nextLine());
    } catch (NumberFormatException e) {
      System.out.println(e.getMessage());
      keyScan.close();
      return;
    }
    try(
        Socket socket = new Socket(serverAddr, port);
        PrintStream out = new PrintStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream (
            socket.getInputStream())) {

      System.out.print("서버에 보낼 메시지 입력:");
      String sendMsg = keyScan.nextLine();

      out.println(sendMsg);
      System.out.println("서버에 메시지 전송함 : hello");

      String message = in.readUTF();
      System.out.println("서버에 메시지 수신함");

      // 서버가 받은 메시지를 출력한다.
      System.out.println("서버 : " + message);
      if(message.equals("OK")) {
        List<Board> list= (List<Board>)in.readObject();
        for(Board b : list) {
          System.out.println(b);
        }
      }

      System.out.println("서버와 연결종료");
    } catch (Exception e) {
      System.out.println("예외발생 : " + e.getMessage());
    }
    
    
    keyScan.close();
  }
  
  public static void main(String[] args) {
    testBoardList();
  }
}
