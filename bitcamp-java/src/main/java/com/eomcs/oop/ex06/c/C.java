package com.eomcs.oop.ex06.c;

public class C {
  
  private void m1() {}   
  // 현재 클래스에서만 접근가능

  void m2() {}           
  // 현재 클래스 + 같은패키지클래스만

  protected void m3() {} 
  // 현재 클래스 + 같은패키지클래스 + 서브클래스에서만 접근가능.

  public void m4() {}    
  // 모두 공개 
}
