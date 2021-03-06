package com.eomcs.lms.handler;

import java.sql.Date;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.prompt.Prompt;

public class MemberAddCommand implements Command {
  MemberDao memberDao;
  Prompt prompt;

  public MemberAddCommand(MemberDao memberDao, Prompt prompt) {
    this.prompt = prompt;
    this.memberDao = memberDao;
  }
  
  @Override
  public void execute() {
    Member member = new Member();

    member.setName(prompt.inputString("이름? "));
    member.setEmail(prompt.inputString("이메일? "));
    member.setPassword(prompt.inputString("암호? "));
    member.setPhoto(prompt.inputString("사진? "));
    member.setTel(prompt.inputString("전화? "));
    member.setRegisteredDate(new Date(System.currentTimeMillis()));

    try {
      memberDao.insert(member);

      System.out.println("저장하였습니다.");

    } catch (Exception e) {
      System.out.println("멤버 추가 저장 중 오류발생");
    }
  }
}
