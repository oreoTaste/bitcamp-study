// 예외 처리 후 마무리 작업 - try-with-resources
package com.eomcs.exception.ex3;

public class Exam0641 {

  static class A {}

  static class B implements AutoCloseable {
    
    public void m(int a) throws Exception {
      if( a > 0)
      System.out.println("m() 수행");
      else throw new Exception("예외발생");
    }
    
    @Override
    public void close() throws Exception {
      System.out.println("B 클래스의 자원을 해제하였습니다.");
    }
  }

  static void m() throws Exception {
      System.out.println("close() 호출");
  }
  
  public static void main(String[] args) throws Exception {
    try (B obj = new B();) {
      System.out.println("TRY블록 실행...시작");
      obj.m(-100);
      System.out.println("TRY블록 실행...종료");
      // 예외가 발생하면 try 블럭을 나가기전에 close()가 호출된다
    } catch (Exception e) {
      System.out.println("예외발생 :" + e.getMessage());
    }

    
    
    
  }
}
