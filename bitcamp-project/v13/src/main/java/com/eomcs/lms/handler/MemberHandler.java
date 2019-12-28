package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.domain.Member;

public class MemberHandler {
  
  //개별 관리
   int memberCount = 0;
  Member[] member = new Member[SIZE];
  
  //공유 관리
  static final int SIZE = 100;
  public static Scanner scanner; // 다른 패키지에서 사용할 수 있도록 public으로 공개처리!
  
  public void listMember() {
    for(int i=0 ; i < this.memberCount ; i++) {
      Member m = this.member[i];
      System.out.printf("%1$d, %2$s , %3$s       , %4$s      , %5$tH:%5$tM:%5$tS\n",
          m.no, m.name,  m.email,  m.tel , m.registeredDate );
    }
  }
  

  public void addMember() {
    Member mem = new Member();
    System.out.print("번호?");
    mem.no = scanner.nextInt();
    scanner.nextLine(); // 빈칸제거
    System.out.print("이름? ");
    mem.name = scanner.nextLine();
    System.out.print("이메일? ");
    mem.email = scanner.nextLine();
    System.out.print("비밀번호? ");
    mem.password = scanner.nextLine();
    System.out.print("사진? ");
    mem.photo = scanner.nextLine();
    System.out.print("전화? ");
    mem.tel = scanner.nextLine();
    mem.registeredDate = new Date(System.currentTimeMillis());
    
    this.member[this.memberCount++] = mem;
    System.out.println("저장하였습니다.");
  }
  

}
