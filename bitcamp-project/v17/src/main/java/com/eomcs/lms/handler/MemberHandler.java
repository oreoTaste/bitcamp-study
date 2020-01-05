package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.domain.Member;

public class MemberHandler {
  ArrayList memberList;

  public Scanner input; // 다른 패키지에서 사용할 수 있도록 public으로 공개처리!

  public MemberHandler(Scanner input) {
    this.input = input;
    memberList = new ArrayList();
  }

  public void listMember() {
    Object[] mbr = memberList.toArray();
    for(Object mb : mbr) {
      Member m = (Member) mb;
        System.out.printf("%1$d, %2$s , %3$s       , %4$s      , %5$tH:%5$tM:%5$tS\n",
            m.getNo(), m.getName(),  m.getEmail(),  m.getTel() , m.getRegisteredDate());
    }
  }

  public void addMember() {
    Object object = new Member();
    Member mem = (Member)object;
    System.out.print("번호?");
    mem.setNo(input.nextInt());
    input.nextLine(); // 빈칸제거
    System.out.print("이름? ");
    mem.setName(input.nextLine());
    System.out.print("이메일? ");
    mem.setEmail(input.nextLine());
    System.out.print("비밀번호? ");
    mem.setPassword(input.nextLine());
    System.out.print("사진? ");
    mem.setPhoto(input.nextLine());
    System.out.print("전화? ");
    mem.setTel(input.nextLine());
    mem.setRegisteredDate(new Date(System.currentTimeMillis()));
    
    memberList.add(mem);
    System.out.println("저장하였습니다.");
  }


}
