// 게시물 관리 - 목록
package com.eomcs.jdbc.ex2.prepareStatementPractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// 다음과 같이 게시물 목록을 출력하라!
// 게시물 번호를 내림차순으로 정렬하라.
// ----------------------------
// 번호, 제목, 등록일, 조회수
// 2, aaa, 2019-1-1, 2
// 1, bbb, 2018-12-31, 3
// ----------------------------
public class Exam0120_select {
  public static void main(String[] args) {
    System.out.println("----------------------------");
    System.out.println("번호,\t\t\t제목,\t\t\t등록일,\t\t조회수");

    try(
        Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/studydb?user=study&password=1111");
        PreparedStatement stmt = con.prepareStatement(
            "select board_id, title, date(created_date), view_count "
            + "from x_board order by board_id DESC");
        ) {

      ResultSet rs = stmt.executeQuery();

      while(rs.next()) {
        System.out.printf("%d,\t\t\t%s,\t\t\t%s,\t\t%d\n",
            rs.getInt("board_id"),
            rs.getString("title"),
            rs.getString("date(created_date)"),
            rs.getInt("view_count"));
      }
      System.out.println("----------------------------");
    } catch(Exception e) {

    }

  }
}

/*
 * Connection con = DriverManager.getConnection( //
        "jdbc:mysql://localhost:3306/studydb?user=study&password=1111"
 */

