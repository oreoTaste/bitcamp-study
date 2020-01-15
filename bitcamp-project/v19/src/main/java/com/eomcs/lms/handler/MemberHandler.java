package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.ArrayList;

public class MemberHandler{
  ArrayList<Member> memberList;

  public Scanner input; // 다른 패키지에서 사용할 수 있도록 public으로 공개처리!

  public MemberHandler(Scanner input) {
    this.input = input;
    this.memberList = new ArrayList();
  }

  public void listMember() {
    
    Member[] arr= this.memberList.toArray(new Member[] {});
    for(Member m : arr) {
        System.out.printf("%1$d, %2$s , %3$s       , %4$s      , %5$tH:%5$tM:%5$tS\n",
            m.getNo(), m.getName(),  m.getEmail(),  m.getTel() , m.getRegisteredDate());
    }
  }

  public void addMember() {
    Member mem;
    mem = new Member();
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
  
  
  public void updateMember() {
    System.out.print("인덱스 번호? ");
    int idx = input.nextInt();
    input.nextLine();

    Member oldMember = memberList.get(idx);

    if(oldMember == null) {
      System.out.println("게시물 번호가 유효하지 않습니다.");
      return;
    }
    
    Member newMember;
    newMember = new Member();
    System.out.print("번호?");
    int tempNo = input.nextInt();
    input.nextLine(); // 빈칸제거

    System.out.print("이름? ");
    String tempName = input.nextLine();
    if(tempName.length() == 0) {
      System.out.println("게시물 변경을 취소했습니다.");
      return;
    }
    
    System.out.print("이메일? ");
    String tempEmail = input.nextLine();
    
    
    System.out.print("비밀번호? ");
    String tempPassword = input.nextLine();
    
    System.out.print("사진? ");
    String tempPhoto = input.nextLine();
    
    System.out.print("전화? ");
    String tempTel = input.nextLine();
    
    newMember.setNo(tempNo);
    newMember.setName(tempName);
    newMember.setEmail(tempEmail);
    newMember.setPassword(tempPassword);
    newMember.setPhoto(tempPhoto);
    newMember.setTel(tempTel);
    newMember.setRegisteredDate(new Date(System.currentTimeMillis()));
    
    this.memberList.set(idx,newMember);
    System.out.println("게시글을 변경했습니다.");
  }

  public void deleteMember() {
    System.out.print("인덱스 번호? ");
    int idx = input.nextInt();
    input.nextLine();

    Member member = memberList.get(idx);

    if(member == null) {
      System.out.println("게시물 번호가 유효하지 않습니다.");
      return;
    }

    this.memberList.remove(idx, member);
    System.out.println("게시글을 삭제했습니다.");
  }


}
