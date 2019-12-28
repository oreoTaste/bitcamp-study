// 변수 초기화 - 자동 초기화
package com.eomcs.oop.ex03;

public class Exam0510 {
    static class A {
      static int b2;
      static int s2;
      static int i2;
      static int l2;
      static String c2;
      

    }
    
    
    public static void main(String[] args) {
        int c;
        
        // 클래스 변수는 생성되는 순간 0으로 자동 초기화 된다.
        System.out.printf("%d, %d, %d, %d, %c, %.1f, %.1f %b, %s, %s\n",
            A.b2, A.s2, A.i2, A.l2, A.c2, 
            A.f2, A.d2, A.bool2, A.str2, A.date2);
        
        A obj = new A();
        
        // 인스턴스 변수는 생성되는 순간 0으로 자동 초기화 된다. 
        System.out.printf("%d, %d, %d, %d, %c, %.1f, %.1f %b, %s, %s\n",
            obj.b2, obj.s2, obj.i2, obj.l2, obj.c2, 
            obj.f2, obj.d2, obj.bool2, obj.str2, obj.date2);
        
        // 로컬 변수는 자동 초기화되지 않는다.
        // 반드시 사용전에 값을 저장해야 한다.
        //System.out.println(c); // 컴파일 오류!
    } 
}




