package com.eomcs.oop.ex02.study;

class Score {
  String n_ame;
  int k_or;
  int e_ng;
  int m_ath;
  int s_um;
  float a_ver;
  
  public void calculate() {
    this.s_um = this.k_or + this.e_ng + this.m_ath;
    this.a_ver = this.s_um / 3f;
  }
}
