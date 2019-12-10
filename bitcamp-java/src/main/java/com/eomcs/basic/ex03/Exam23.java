package com.eomcs.basic.ex03;

public class Exam23 {
  public static void main(String[] args){
    //4바이트 (32비트)
    System.out.println(-2_147_483_648); // -2^31
    System.out.println(2_147_483_647); //2^31 -1

    //8바이트(64비트)
    System.out.println(-9_223_372_036_854_775_808L);
    System.out.println(9_223_372_036_854_775_807L);
    System.out.println(Integer.MAX_VALUE);
    System.out.println(Integer.MIN_VALUE);
    System.out.println(Long.MAX_VALUE);
    System.out.println(Long.MIN_VALUE);

    
  }
}