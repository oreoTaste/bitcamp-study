// 게시판 관리 - 삭제
package com.eomcs.jdbc.ex2.prepareStatementPractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

// 다음과 같이 게시물을 삭제하는 프로그램을 작성하라!
// ----------------------------
// 번호? 1
// 삭제하였습니다.
// (또는)
// 해당 번호의 게시물이 존재하지 않습니다.
// ----------------------------
public class Exam0150_delete {
  static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    
    System.out.print("번호? ");
    int no = Integer.parseInt(scanner.nextLine());


    try(
        Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/studydb?user=study&password=1111");
        
        PreparedStatement stmt = con.prepareStatement(
            "delete from x_board where board_id = ?");
        ) {
      
      stmt.setInt(1, no);
      int result = stmt.executeUpdate();
      
      if(result != 0) {
        System.out.printf("%d개의 게시물을 삭제하였습니다.\n", result);
      } else {
        System.out.println("해당 번호의 게시물이 존재하지 않습니다.");
      }

    } catch(Exception e) {

    }
    
  }
  
}


