package com.eomcs.oop.ex06.c;

public class C4 extends C {

  // => 접근 범위를 좁힐수는 없다.
  //@Override
  //private void m2() {} // protected 보다 접근 범위를 확대했기 때문에 OK!

  //@Override
  //void m3() {} // 컴파일 오류! (default) ==> private

  //@Override
  //protected void m4() {} // OK! (default) ==> public

  //@Override private void m4() {} // 컴파일 오류! public ==> private
  //@Override void m4() {} // 컴파일 오류! public ==> (default)
  //@Override protected void m4() {} // 컴파일 오류! public ==> protected

}

// 멤버의 접근 범위
// private      : 같은 클래스
// (default)    : 같은 클래스 + 같은 패키지
// protected    : 같은 클래스 + 같은 패키지 + 서브 클래스
// public       : 모두
//
// 결론!
// 수퍼 클래스의 메서드를 자식 클래스가 재정의할 때 
// 접근 범위를 확대할 순 있지만, 좁힐 수는 없다!







