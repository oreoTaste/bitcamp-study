// stateless 방식에서 클라이언트를 구분하고 작업 결과를 유지하는 방법 - 계산기 서버 만들기
package com.eomcs.net.ex04.stateless3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class CalcServer {
  
  static class RequestHandler extends Thread {
    Socket socket;
    
    public RequestHandler(Socket socket) {
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
  
  // Map <클라이언트ID, Result>
  static Map<Long, Integer> resultMap = new HashMap<>();
  
  public static void main(String[] args) throws Exception {
    System.out.println("서버 실행 중...");

    ServerSocket ss = new ServerSocket(8888);

    while (true) {
      Socket socket = ss.accept();
      System.out.println("클라이언트 연결됨");
      try {
        
        System.out.print(socket.getRemoteSocketAddress().toString().substring(0,
            socket.getRemoteSocketAddress().toString().indexOf(":")) + " ");
        
        System.out.println(socket.getRemoteSocketAddress().toString().substring(
            socket.getRemoteSocketAddress().toString().indexOf(":")+1));
        
        RequestHandler requestHandler = new RequestHandler(socket);
        requestHandler.start();
        System.out.printf("%s 클라이언트의 요청을 쓰레드에게 맡겼습니다\n",
            socket.getRemoteSocketAddress().toString().substring(0,
                socket.getRemoteSocketAddress().toString().indexOf(":")));
        
        
      } catch (Exception e) {
        System.out.println("클라이언트 요청 처리 중 오류 발생!");
        System.out.println("다음 클라이언트의 요청을 처리합니다.");
      } finally {
        System.out.println("연결종료");
      }
    }
    //ss.close();
  }

  @SuppressWarnings("static-access")
  static void processRequest(Socket socket) throws Exception {
    try (
        Socket socket2 = socket;
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        ) {

      // 클라이언트 구분ID
      // => 0 : 첫방문자
      // => 기타 : 서버가 클라이언트에게 아이디를 생성함
      Long clientId = in.readLong();
      
      // 연산자와 값 받기
      String op = in.readUTF();
      int value = in.readInt();

      // 클라이언트를 위한 기존값 꺼내기
      int result = 0;
      Integer obj = null;
      obj = resultMap.get(clientId);
      if(obj != null) {
        System.out.printf("%d번 기존고객 요청처리\n", obj);
        result = obj;
      } else if(obj == null) {
        clientId = System.currentTimeMillis();
        System.out.println("%d번 신규고객 요청처리");
      }

      switch (op) {
        case "+": result += value; break;
        case "-": result -= value; break;
        case "*": result *= value; Thread.currentThread().sleep(10000); break;
        case "/": result /= value; break;
      }

      out.writeLong(clientId);
      out.writeInt(result);
      out.flush();
      System.out.println("클라이언트 연결해제");
      // 계산결과를 출력한후, 계산결과를 resultMap에 보관한다.
      resultMap.put(clientId, result);
    }
  }
}





