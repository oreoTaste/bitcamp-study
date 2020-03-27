package com.eomcs.lms.servlet;

import java.io.PrintWriter;
import java.sql.Date;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.service.BoardService;
import com.eomcs.util.RequestMapping;

@Component
public class BoardUpdateServlet {

  BoardService boardService;

  public BoardUpdateServlet(BoardService boardService) {
    this.boardService = boardService;
  }

  @RequestMapping("/board/update")
  public void service(Map<String, String> map, PrintWriter out) throws Exception {

    printHead(out);
    
    try {
      
      Board newBoard = new Board();
      int no = Integer.parseInt(map.get("no"));
      String title = map.get("title");

      newBoard.setTitle(title);
      newBoard.setNo(no);
      newBoard.setDate(new Date(System.currentTimeMillis()));
      newBoard.setViewCount(0);
 
      out.println("<h1>게시글 변경 결과</h1>");
      if(boardService.update(newBoard)){
        out.println("<p>게시글을 변경했습니다.</p>");
      } else {
        out.println("<p>게시글 변경을 취소했습니다</p>");
      }
    } catch(Exception e) {
      
    }
    
    printTail(out);
  }
  
  private void printTail(PrintWriter out) {
    out.println("</body>");
    out.println("</html>");
  }

  private void printHead(PrintWriter out) {
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv=\"refresh\" content='2; url=/board/list'>");
    out.println("<title> 게시글 수정 </title>");
    out.println("</head>");

    out.println("<body>");
  }
}