package com.eomcs.lms.servlet;

import java.io.PrintWriter;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.service.BoardService;
import com.eomcs.util.RequestMapping;

@Component
public class BoardDetailServlet {

  BoardService boardService;

  public BoardDetailServlet(BoardService boardService) {
    this.boardService = boardService;
  }

  @RequestMapping("/board/detail")
  public void service(Map<String, String> map, PrintWriter out) throws Exception {

    int no = Integer.parseInt(map.get("no"));
    Board board = boardService.get(no);

    printHead(out);
    
    if(board != null) {
      out.printf("번호: %d<br>", board.getNo());
      out.printf("제목: %s<br>", board.getTitle());
      out.printf("등록일: %1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS<br>", board.getDate());
      out.printf("조회수: %d<br>", board.getViewCount());
      out.printf("<div><a href='/board/delete?no=%d'>삭제</a>", board.getNo());
      out.printf("  ..  ");
      out.printf("<a href='/board/updateForm?no=%d'>변경</a>", board.getNo());
    } else {
      out.println("해당 번호의 게시물이 없습니다.<br>");
      out.flush();
    }
    out.println("  ..  ");
    out.println("<a href='/board/list'>게시글 목록으로 돌아가기</a></div>");
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
//    out.println("<meta http-equiv='refresh' content='3; url=/board/list'>");
    out.println("<title>게시글 상세정보</title>");
    out.println("</head>");
    out.println("<body>");
  }
}
