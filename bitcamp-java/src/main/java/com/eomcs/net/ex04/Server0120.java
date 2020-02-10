// 통신방식 - Stateful 방식

// stateful 방식
// => 서버와 연결한 후, 클라이언트에서 연결을 끊을 때까지
//   계속해서 연결을 유지하며 클라이언트 요청을 처리한다.
// => 단점:
// - 한 번 연결하면 클라이언트가 연결을 끊을 때까지 계속 유지한다.
// - 클라이언트가 작업을 요청하지 않더라도 계속
//   서버에 연결정보를 계속 유지하기 때문에
//   서버 메모리를 많이 차지하고
//   동시에 많은 클라이언트의 요청을 처리하기 힘들다.
// - 만약 서버가 순차적으로 클라이언트와 연결을 수행한다면
//   이전에 연결했던 클라이언트가 연결을 끊기 전까지는
//   다음 클라이언트와 연결되지 못하는 문제가 있다.
// => 장점:
// - 한 번 서버에 연결되면 클라이언트가 연결을 끊을 때까지 유지되기 때문에
//   요청할 때 마다 매번 연결 작업을 수행할 필요가 없다.
// - 따라서 요청에 대한 응답이 빠르다.
// - 또한 연결된 상태에서 수행한 작업  정보를 서버에 유지할 수 있어
//   영속성이 필요한 작업을 처리하는데 유리하다.
// => 작업 시간:
// - = 데이터 전송 시간 + 작업 처리 시간 + 데이터 수신 시간
// - 즉 작업을 요청할 때마다 연결할 필요가 없기 때문에 연결하는데 시간이 걸리지 않는다.
package com.eomcs.net.ex04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server0120 {
  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);
    try (ServerSocket serverSocket = new ServerSocket(8888)) {

      System.out.println("서버실행");
      while(true) { // 한 클라이언트의 작업이 끝나면 다음 클라이언트와 작업을 수행한다
        try (Socket socket = serverSocket.accept();
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream())) {

          System.out.println("클라이언트가 연결됨.");

          while(true) {
            String name = in.readLine();
            if(name.equalsIgnoreCase("quit")) {
              out.println("good bye");
              out.flush();
              break;
            }
            out.printf("%s님 반갑습니다! \n", name);
            out.flush();
          }
        } catch(Exception e) {
          System.out.println("클라이언트와 통신도중 오류 발생!");
        }
        System.out.println("클라이언트와의 연결을 끊었음.");
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("서버 종료!");
  }

}

