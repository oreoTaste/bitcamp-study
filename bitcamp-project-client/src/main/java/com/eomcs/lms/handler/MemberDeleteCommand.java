package com.eomcs.lms.handler;

import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.prompt.Prompt;

public class MemberDeleteCommand implements Command {
  MemberDao memberDao;
  Prompt prompt;

  public MemberDeleteCommand(MemberDao memberDao, Prompt prompt) {
    this.prompt = prompt;
    this.memberDao = memberDao;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("번호? ");

      memberDao.delete(no);
      System.out.println("회원을 삭제했습니다.");

    } catch (Exception e) {
      System.out.println("멤버 정보 수신 중 오류발생!");
    }
  }
}
