// 게시판 관리 - 등록
package com.eomcs.jdbc.ex2.createStatementPractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// 다음과 같이 게시물을 등록하는 프로그램을 작성하라!
// ----------------------------
// 제목? aaa
// 내용? bbb
// 등록하시겠습니까?(Y/n) y
// 등록하였습니다.
// 등록하시겠습니까?(Y/n) n
// 등록을 취소 하였습니다.
// ----------------------------
public class Exam0110 {
  static Scanner scanner = new Scanner(System.in);
  
  public static void main(String[] args) {
    System.out.print("제목 : ");
    String title = scanner.nextLine();

    System.out.print("내용 : ");
    String contents = scanner.nextLine();

    System.out.print("등록하시겠습니까?(Y/n) ");
    String toRegister = scanner.nextLine();

    if(!toRegister.equalsIgnoreCase("n")) {
      
      try {
        Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/studydb?user=study&password=1111");
        Statement stmt = con.createStatement();
        int result = stmt.executeUpdate(
            "insert into x_board (title, contents) values ('"+ title + "','" + contents +"')");
        System.out.println(result + "개 등록완료!");
        
        
      } catch (SQLException e) {
        e.printStackTrace();
      }
      
    }
    System.out.println("등록을 취소 하였습니다.");

    
  }
}

/*
 * Connection con = DriverManager.getConnection( //
        "jdbc:mysql://localhost:3306/studydb?user=study&password=1111"
 */


