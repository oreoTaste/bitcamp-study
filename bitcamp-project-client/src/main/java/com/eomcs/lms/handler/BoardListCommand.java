package com.eomcs.lms.handler;

import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.util.List;
import com.eomcs.lms.domain.Board;

public class BoardListCommand implements Command {
  List<Board> boardList;

  PrintStream out;
  ObjectInputStream in;
  
  public BoardListCommand(PrintStream out, ObjectInputStream in) {
    this.out = out;
    this.in = in;
  }
  
  public BoardListCommand(List<Board> list) {
    boardList = list;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void execute() {
    try {
      out.println("/board/list");
      String response = in.readUTF();
      if(response.equals("FAIL")) {
        // fail이유까지 읽기
        System.out.println(in.readUTF());
        return;
      } else if(response.equals("OK")) {
        List<Board> boards = (List<Board>) in.readObject();
        
        for (Board b : boards) {
          System.out.printf("%1$d, %2$s, %3$tF %3$tH:%3$tM:%3$tS, %4$d\n", b.getNo(), b.getTitle(),
              b.getDate(), b.getViewCount());
        }
      }
      
    } catch (Exception e) {
      System.out.println("통신오류 발생");
    }

  }



}
