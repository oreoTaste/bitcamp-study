package com.eomcs.basic.ex02;

public class Exam3 {
  public static void main(final String[] args) {

    System.out.println("annotation");


  }
  @Deprecated
  public String toString(String str) {
    return str;
  }
  
  @Override 
  public String toString(){
    return "okok";
  }
}