package com.eomcs.basic.ex04;

public class Exam41 {
    public static void main(String[] args) {
      //문자변수
      short s;
      char c;

      s = -32768;
      s = 32767;

      c = 0;
      c = 65535;
      char c1 = 65;
      char c2 = 0x41;
      char c3 = 0b0100_0001;
      char c4 = 'A';
      System.out.println(c1 + "\t" + c2 + "\t" + c3 + "\t" + c4);

      for(int i=65 ; i<'A'+26 ; i++){
        System.out.print((char)i + " ");
      }
    }
}
