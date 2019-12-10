package com.eomcs.basic.ex03;

import java.math.BigDecimal;

public class Exam42 {
  public static void main(String[] args){
    //System.out.println(0x0041);
    //System.out.println(0xac00);

    //System.out.println((char)0x0041);
    //System.out.println((char)0xac00);

    for (int i = 0 ; i < 11172 ; i++){
      if (i%80 == 0)
        System.out.println();
      System.out.print((char)(0xac00+i));
    }
  }
}