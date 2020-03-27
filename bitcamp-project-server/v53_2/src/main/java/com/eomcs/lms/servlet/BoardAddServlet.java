package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.service.BoardService;
import com.eomcs.util.RequestMapping;

@Component
public class BoardAddServlet {
  BoardService boardService;

  public BoardAddServlet(BoardService boardService) {
    this.boardService = boardService;
  }

  @RequestMapping("/board/add")
  public void service(Map<String, String> map, PrintStream out) throws Exception {
    
    printHead(out);
    
    Board board = new Board();
    String title = map.get("title");
    board.setTitle(title);

    out.println("<h1>입력결과</h1>");
    if (boardService.add(board)) { // 삭제했다면
      out.println("<p>새 글을 등록했습니다.</p>");
    } else {
      out.println("<p>게시물 등록에 실패했습니다.</p>");
    }
    
    printTail(out);
  }
  
  private void printTail(PrintStream out) {
    out.println("</body>");
    out.println("</html>");
  }

  private void printHead(PrintStream out) {
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv=\"refresh\" content='2; url=/board/list'>");
    out.println("<title> 게시글 목록 </title>");
    out.println("</head>");

    out.println("<body>");
  }
  
}
