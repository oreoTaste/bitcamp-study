// 던지는 예외 받기 - try ~ catch ~
package com.eomcs.exception.ex3;

import java.io.IOException;
import java.sql.SQLException;

public class Exam0430 {

  static void m(int i)
      throws Exception, RuntimeException, SQLException, IOException {
    if (i == 0)
      throw new Exception();
    else if (i == 1)
      throw new RuntimeException();
    else if (i == 2)
      throw new SQLException();
    else if (i == 3)
      throw new IOException();
  }

  public static void main(String[] args) throws RuntimeException, SQLException, IOException, Exception {
    try {
      // try 블록에서 예외가 발생할 수 있는 메서드를 호출한다.
      m(3);
      System.out.println("실행성공");

    } catch (IOException e) {
      System.out.println("IOException 발생");

    } catch (SQLException e) {
      System.out.println("SQLException 발생");

    } catch (RuntimeException e) {
      System.out.println("RuntimeException 발생");

    } catch (Exception e) {
      System.out.println("기타 Exception 발생");

    }
  }

}
