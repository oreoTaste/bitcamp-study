package com.eomcs.lms.servlet;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.service.BoardService;
import com.eomcs.util.RequestMapping;

@Component
public class BoardListServlet {

  BoardService boardService;

  public BoardListServlet(BoardService boardService) {
    this.boardService = boardService;
  }

  @RequestMapping("/board/list")
  public void service(Map<String, String> map, PrintWriter out) throws Exception {

    printHead(out);


    out.println("<table border='1'>");
    out.println("<tr>");
    out.println("<th>번호</th>");
    out.println("<th>제목</th>");
    out.println("<th>등록일</th>");
    out.println("<th>조회수</th>");
    out.println("</tr>");

    List<Board> boards = boardService.list();

    for(Board board : boards) {

      out.printf("<tr>");
      out.printf("<td><a href='/board/detail?no=%d'>%d</a></td>", board.getNo(), board.getNo());
      out.printf("<td><a href='/board/detail?no=%d'>%s</a></td>", board.getNo(), board.getTitle());
      out.printf("<td><a href='/board/detail?no=%d'>%s</a></td>", board.getNo(), board.getDate());
      out.printf("<td><a href='/board/detail?no=%d'>%d</a></td>", board.getNo(), board.getViewCount());
      out.printf("</tr>"); //
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
    out.println("<title> 게시글 목록 </title>");
    out.println("</head>");

    out.println("<body>");
    out.println("<h1>게시글</h1>");
    out.println("<a href='/board/addForm'>새글</a><br>");
  }

}















