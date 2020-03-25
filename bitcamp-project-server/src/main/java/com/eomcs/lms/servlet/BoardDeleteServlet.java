package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.eomcs.lms.service.BoardService;
import com.eomcs.util.RequestMapping;

@Component
public class BoardDeleteServlet {

  BoardService boardService;

  public BoardDeleteServlet(BoardService boardService) {
    this.boardService = boardService;
  }

  @RequestMapping("/board/delete")
  public void service(Map<String, String> map, PrintStream out) throws Exception {

    printHead(out);
    
    try {
      out.println("<h1>게시글 삭제 결과</h1>");
      int no = Integer.parseInt(map.get("no"));
      if(boardService.delete(no)) {
        out.println("<p>게시글을 삭제했습니다.</p>");
      } else {
        out.println("<p>해당 번호의 게시글이 없습니다.</p>");
      }

    } catch (Exception e) {
      out.println("<p>게시판 정보 삭제 중 오류발생!</p>");
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
    out.println("<title> 게시글 삭제 </title>");
    out.println("</head>");

    out.println("<body>");
  }
  
}
