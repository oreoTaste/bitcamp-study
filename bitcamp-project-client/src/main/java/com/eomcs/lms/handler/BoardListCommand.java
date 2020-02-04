package com.eomcs.lms.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import com.eomcs.lms.domain.Board;

public class BoardListCommand implements Command {
  List<Board> boardList;

  ObjectOutputStream out;
  ObjectInputStream in;
  
  public BoardListCommand(ObjectOutputStream out, ObjectInputStream in) {
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
      out.writeUTF("/board/list");
      // blocking 상태!
      out.flush(); // 서버에 데이터를 즉시 전송하도록 임시 버퍼의 내용을 방출한다.
      // ObjectOutputStream은 내부에 버퍼를 사용한다.
      // 따라서 서버에 즉시 전송하고 싶다면, flush()를 명시적으로 호출해야한다.
      // close()를 호출해도 자동으로 flush()가 수행된다.
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
