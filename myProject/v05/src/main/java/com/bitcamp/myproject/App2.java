package com.bitcamp.myproject;
import java.util.Scanner;
import java.sql.Date;
import java.util.Calendar;

public class App2 {
  public static void main(String[] args) {
    java.io.InputStream inputStream = System.in;
    Scanner scanner = new Scanner(inputStream);
    System.out.print("번호? ");
    int no = scanner.nextInt();
    scanner.nextLine(); // nextInt와 nextLine 사이의 공란 제거(커서)
    System.out.print("이름? ");
    String name = scanner.nextLine();
    System.out.print("이메일? ");
    String email = scanner.nextLine();
    System.out.print("암호? ");
    String password = scanner.nextLine();
    System.out.print("사진? ");
    String photo = scanner.nextLine();
    System.out.print("전화? ");
    String tel = scanner.nextLine();
    System.out.println();
    scanner.close();
    
    System.out.printf("번호: %d\n", no);
    System.out.printf("이름: %s\n", name);
    System.out.printf("이메일: %s\n", email);
    System.out.printf("암호: %s\n", password);
    System.out.printf("사진: %s\n", photo);
    System.out.printf("전화: %s\n", tel);
    Calendar calendar = Calendar.getInstance();
    System.out.printf("가입일: %tF\n", calendar);
    
    //Date registeredDate = new Date(System.currentTimeMillis());
    //System.out.printf("가입일: %tF\n", registeredDate);
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