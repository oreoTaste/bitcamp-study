package com.eomcs.lms;

import java.util.Scanner;

public class App2 {
  public static void main(String[] args) {
    Scanner keyboard = new java.util.Scanner(System.in);
    System.out.println("번호? ");
    String no = keyboard.nextLine();
    System.out.println("이름? ");
    String name = keyboard.nextLine();
    System.out.println("이메일? ");
    String emailAddress = keyboard.nextLine();
    System.out.println("암호? ");
    String password = keyboard.nextLine();
    System.out.println("사진? ");
    String photo = keyboard.nextLine();
    System.out.println("전화? ");
    String telNo = keyboard.nextLine();
    
    System.out.println();
    System.out.printf("번호: %s\n", no);
    System.out.printf("이름: %s\n", name);
    System.out.printf("이메일: %s\n", emailAddress);
    System.out.printf("암호: %s\n", password);
    System.out.printf("사진: %s\n", photo);
    System.out.printf("전화: %s\n", telNo);
    System.out.printf("가입일: 2019-01-01\n");
    
  }
}
/*
 번호? 1
이름? 홍길동
이메일? hong@test.com
암호? 1111
사진? hong.png
전화? 1111-2222

번호: 1
이름: 홍길동
이메일: hong@test.com
암호: 1111
사진: hong.png
전화: 1111-2222
가입일: 2019-01-01
 
 */
