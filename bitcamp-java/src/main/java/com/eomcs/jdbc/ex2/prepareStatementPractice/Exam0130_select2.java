// 게시물 관리 - 조회
package com.eomcs.jdbc.ex2.prepareStatementPractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

// 다음과 같이 게시물을 조회하는 프로그램을 작성하라!
// ----------------------------
// 번호? 1
// 제목: aaa
// 내용: aaaaa
// 등록일: 2019-1-1
// 조회수: 2
//
// 번호? 100
// 해당 번호의 게시물이 존재하지 않습니다.
// ----------------------------
public class Exam0130_select2 {
  static Scanner scanner = new Scanner(System.in);
  
  public static void main(String[] args) {
    System.out.print("번호? ");
    int no = Integer.parseInt(scanner.nextLine());
    
    try(
        Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/studydb?user=study&password=1111");
        PreparedStatement stmt = con.prepareStatement(
            "select board_id, title, contents, date(created_date), view_count"
            + " from x_board"
            + " where board_id = ?");
        ) {
      stmt.setInt(1, no);
      ResultSet rs = stmt.executeQuery();
      
      if(rs.next()) {
        System.out.println("제목: " + rs.getString("title"));
        System.out.println("내용: " + rs.getString("contents"));
        System.out.println("등록일: " + rs.getString("date(created_date)"));
        System.out.println("조회수: " + rs.getInt("view_count"));
      } else {
        System.out.println("해당 번호의 게시물이 존재하지 않습니다.");
      }
      
    } catch(Exception e) {
      
    }
  }
  
}

/*
 * Connection con = DriverManager.getConnection( //
        "jdbc:mysql://localhost:3306/studydb?user=study&password=1111"
 */

