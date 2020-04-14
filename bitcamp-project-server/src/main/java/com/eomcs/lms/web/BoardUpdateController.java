package com.eomcs.lms.web;

import java.sql.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.service.BoardService;
import com.eomcs.lms.util.RequestMapping;


@Component
public class BoardUpdateController {
  @Autowired
  BoardService boardService;

  @RequestMapping("/board/update")
  public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {

      if(request.getMethod().equals("GET")) {

        int no = Integer.parseInt(request.getParameter("no"));
        Board board = boardService.get(no);

        response.setContentType("text/html;charset=UTF-8");
        request.setAttribute("board", board);
        return "/board/updateform.jsp";
      }

      Board newBoard = new Board();
      int no = Integer.parseInt(request.getParameter("no"));
      String title = request.getParameter("title");

      newBoard.setTitle(title);
      newBoard.setNo(no);
      newBoard.setDate(new Date(System.currentTimeMillis()));
      newBoard.setViewCount(0);

      if(boardService.update(newBoard))
        return "redirect:list";
      else
        throw new Exception("수정할 게시물 정보가 유효하지 않습니다.");

  }

}