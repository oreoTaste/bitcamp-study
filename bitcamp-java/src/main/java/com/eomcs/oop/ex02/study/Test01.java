package com.eomcs.oop.ex02.study;


public class Test01 {
  public static void main(String[] args) {
    Score[] sc = new Score[100];
    
    Score in = new Score();
    in.n_ame = "홍길동";
    in.k_or = 10;
    in.e_ng = 100;
    in.m_ath = 100;
    in.calculate();
    sc[0] = in;
    System.out.println(in.a_ver);
    System.out.println(in.s_um);
  }
}
