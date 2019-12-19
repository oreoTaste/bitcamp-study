package com.eomcs.lms;

import java.util.Calendar;
import java.sql.Date;
import java.util.Scanner;

public class App2 {
  /**
   * @param args
   */
  public static void main(String[] args) {

    int count = 0;
    final int SIZE = 100;
    
    class Lesson {
      int no;
      String name;
      String email;
      String password;
      String photo;
      String tel;
      Date registeredDate;
    }
    Lesson[] lessons = new Lesson[SIZE];


    java.io.InputStream inputStream = System.in;
    java.util.Scanner scanner = new java.util.Scanner(inputStream);


    for(count = 0 ; count<SIZE ; count++) {
      Lesson les = new Lesson();
      lessons[count] = les;

      System.out.print("번호?");
      les.no = scanner.nextInt();
      scanner.nextLine(); // 빈칸제거
      System.out.print("이름? ");
      les.name = scanner.nextLine();
      System.out.print("이메일? ");
      les.email = scanner.nextLine();
      System.out.print("비밀번호? ");
      les.password = scanner.nextLine();
      System.out.print("사진? ");
      les.photo = scanner.nextLine();
      System.out.print("전화? ");
      les.tel = scanner.nextLine();
      les.registeredDate = new Date(System.currentTimeMillis());
      System.out.println();
      System.out.print("계속 입력하시겠습니까?(y/n)");
      String repeat = scanner.nextLine();
      if(repeat.equals("Y") || repeat.equals("y")) continue;
      else break;
    } scanner.close();

    System.out.println();

      for(int i=0 ; i<count+1 ; i++) {
        Lesson les = lessons[i];
        System.out.printf("%1$d, %2$s , %3$s       , %4$s      , %5$tH:%5$tM:%5$tS\n",
            les.no, les.name,  les.email,  les.tel , les.registeredDate );
    }
  }
}
  /*
번호? 1
이름? 홍길동
이메일? hong@test.com
암호? 1111
사진? hong.png
전화? 1111-2222

계속 입력하시겠습니까?(Y/n) y

번호? 2
이름? 임꺽정
이메일? lim@test.com
암호? 1111
사진? lim.png
전화? 1111-2223

계속 입력하시겠습니까?(Y/n) y

번호? 3
이름? 전봉준
이메일? jeon@test.com
암호? 1111
사진? jeon.png
전화? 1111-2224

계속 입력하시겠습니까?(Y/n) n

1, 홍길동 , hong@test.com       , 1111-2222      , 2019-01-01
2, 임꺽정 , lim@test.com        , 1111-2223      , 2019-01-01
3, 전봉준 , jeon@test.com       , 1111-2224      , 2019-01-01
   */