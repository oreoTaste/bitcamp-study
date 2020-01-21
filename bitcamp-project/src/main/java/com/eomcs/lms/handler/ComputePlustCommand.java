// /board/detail 명령 수행
package com.eomcs.lms.handler;

import com.eomcs.util.Prompt;

public class ComputePlustCommand implements Command {
  
  Prompt prompt;
  
  public ComputePlustCommand(Prompt prompt) {
    this.prompt = prompt;
  }
  
  @Override
  public void execute() {
    double num1 = prompt.inputInt("수1? ");
    double num2 = prompt.inputInt("수2? ");
    System.out.printf("계산결과는 %s입니다", num1 + num2);
    
  }

}









