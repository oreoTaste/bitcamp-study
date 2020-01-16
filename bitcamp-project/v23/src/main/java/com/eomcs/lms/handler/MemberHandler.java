package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.LinkedList;
import com.eomcs.util.List;
import com.eomcs.util.Prompt;

public class MemberHandler {
  List<Member> memberList;
  public Scanner input;
  public Prompt prompt;

  public MemberHandler(Prompt prompt, List<Member> list) {
    this.prompt = prompt;
    memberList = list;
  }
/*
  public MemberHandler(Prompt prompt, int capacity) {
    this.prompt = prompt;
    memberList = new LinkedList<>(capacity);
  }
  */

  ////////////////////////////////////////////////////////////////

  public void listMember() {
    Member[] member = new Member[this.memberList.size()];
    memberList.toArray(member);
    for(Member m : member) {
      System.out.printf("%1$d, %2$s , %3$s       , %4$s      , %5$tH:%5$tM:%5$tS\n",
          m.getNo(), m.getName(),  m.getEmail(),  m.getTel() , m.getRegisteredDate() );
    }
  }

  ////////////////////////////////////////////////////////////////

  public void addMember() {
    Member mem = new Member();
    
    mem.setNo(prompt.inputInt("번호? "));
    mem.setName(prompt.inputString("이름? "));
    mem.setEmail(prompt.inputString("이메일? "));
    mem.setPassword(prompt.inputString("비밀번호? "));
    mem.setPhoto(prompt.inputString("사진? "));
    mem.setTel(prompt.inputString("전화? "));
    mem.setRegisteredDate(new Date(System.currentTimeMillis()));

    memberList.add(mem);
  }

  
  public void deleteMember() {
    int no = prompt.inputInt("번호? ");
    int index = indexOfMember(no);
    if(index == -1) {
      System.out.println("해당 회원을 찾을 수 없습니다.");
      return;
    } 
    this.memberList.remove(index);
    System.out.println("회원을 삭제했습니다.");
  }
  

  public void updateMember() {
    int no = prompt.inputInt("번호? ");
    int index = indexOfMember(no);
    
    if(index == -1) {
      System.out.println("해당 회원을 찾을 수 없습니다.");
      return;
    } 
    
    Member oldMember = this.memberList.get(index);
    Member newMember = new Member();
    
    //System.out.printf("번호? %d\n", oldMember.getNo());
    newMember.setNo(oldMember.getNo());
    
    newMember.setName(prompt.inputString(String.format(
        "이름? (%s) ", oldMember.getName()),
        oldMember.getName()));
    
    newMember.setEmail(prompt.inputString(String.format(
        "이메일? (%s) ", oldMember.getEmail()),
        oldMember.getEmail()));
    
    newMember.setPassword(prompt.inputString(String.format(
        "비밀번호? (%s) ", oldMember.getPassword()),
        oldMember.getPassword()));
    
    newMember.setPhoto(prompt.inputString(String.format(
        "사진? (%s) ", oldMember.getPhoto()),
        oldMember.getPhoto()));

    newMember.setTel(prompt.inputString(String.format(
        "전화? (%s) ", oldMember.getTel()),
        oldMember.getTel()));
    
    newMember.setRegisteredDate(new Date(System.currentTimeMillis()));
    
    if(oldMember.equals(newMember)) {
      System.out.println("회원정보 변경을 취소했습니다");
    } else {
      oldMember.setRegisteredDate(new Date(System.currentTimeMillis()));
      memberList.set(index, newMember);
      System.out.println("회원을 변경했습니다.");
    }
  }


  public void detailMember() {
    int no = prompt.inputInt("번호? ");
    int index = indexOfMember(no);
    
    if(index == -1) {
      System.out.println("해당 회원을 찾을 수 없습니다.");
      return;
    }
    Member mem = memberList.get(index);
    
    //System.out.printf("번호? %d\n", mem.getNo());
    System.out.printf("이름? %s\n", mem.getName());
    System.out.printf("이메일? %s\n", mem.getEmail());
    System.out.printf("암호? %s\n", mem.getPassword());
    System.out.printf("사진? %s\n", mem.getPhoto());
    System.out.printf("전화? %s\n", mem.getTel());
    System.out.printf("가입일? %tF\n",mem.getRegisteredDate());
    
  }

  private int indexOfMember(int no) {
    for(int i = 0 ; i < this.memberList.size() ; i++) {
      if(this.memberList.get(i).getNo() == no) {
        return i;
      }
    } return -1;
  }

}
