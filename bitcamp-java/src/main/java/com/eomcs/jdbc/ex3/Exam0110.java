// 게시판 관리 - JDBC 코드를 별도의 클래스로 캡슐화시킴. DAO 적용.
package com.eomcs.jdbc.ex3;

import java.util.Scanner;

public class Exam0110 {

  public static void main(String[] args) throws Exception {

    Board board = new Board();

    try (Scanner keyScan = new Scanner(System.in)) {

      // 사용자로부터 제목, 내용을 입력 받는다.
      System.out.print("제목? ");
      board.setTitle(keyScan.nextLine());

      System.out.print("내용? ");
      board.setContent(keyScan.nextLine());

      System.out.print("입력하시겠습니까?(Y/n) ");
      String input = keyScan.nextLine();

      if (!input.equalsIgnoreCase("y") && input.length() != 0) {
        System.out.println("등록을 취소 하였습니다.");
        return;
      }
    }

    try {
      BoardDao boardDao = new BoardDao();
      int count = boardDao.insert(board);
      System.out.printf("%d 개 입력 성공!", count);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    // 도메인객체 (DAO, Data Access Object)
    // DTO (Data Transfer Object) : 데이터를 실어나른다는 의미로
    // VO (Value Object)
    
    // 도메인 객체(예: Board)
    // => 두 객체 사이에 데이터를 실어 나르는 역할을 한다고 해서
    // "DTO(Data Transfer Object)"라고도 부른다.
    // => 두 객체 사이에 전달되는 값을 표현한다고 해서
    // "VO(Value Object)"라고도 부른다.
    
  }
}


