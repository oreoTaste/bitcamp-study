// 게시판 관리 - 변경
package com.eomcs.jdbc.ex2.createStatementPractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

// 다음과 같이 게시물을 변경하는 프로그램을 작성하라!
// ----------------------------
// 번호? 1
// 제목? xxx
// 내용? xxxxx
// 변경하였습니다.
// (또는)
// 해당 번호의 게시물이 존재하지 않습니다.
// ----------------------------
public class Exam0140 {
  static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {

    System.out.print("번호? ");
    int no = Integer.parseInt(scanner.nextLine());

    System.out.print("제목? ");
    String title = scanner.nextLine();

    System.out.print("내용? ");
    String contents = scanner.nextLine();

    try(
        Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/studydb?user=study&password=1111");
        
        Statement stmt = con.createStatement();
        ) {
      int result = stmt.executeUpdate(
          "update x_board set title = '" + title + "', contents = '" + contents + "' where board_id = " + no);
      
      if(result != 0) {
        System.out.printf("%d개의 게시물을 변경하였습니다.\n", result);
      } else {
        System.out.println("해당 번호의 게시물이 존재하지 않습니다.");
      }

    } catch(Exception e) {

    }

  }
}


