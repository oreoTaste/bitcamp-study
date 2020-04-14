package com.eomcs.lms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.service.BoardService;
import com.eomcs.lms.util.RequestMapping;

@Component
public class BoardAddController {

  @Autowired
  BoardService boardService;

  @RequestMapping("/board/add")
  public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
    if(request.getMethod().equals("GET")) {
      response.setContentType("text/html;charset=UTF-8");
      return "/board/form.jsp";
    }

    Board board = new Board();
    board.setTitle(request.getParameter("title"));
    if(boardService.add(board))
      return "redirect:list";
    else
      throw new Exception("추가할 게시물 정보가 유효하지 않습니다.");

  }



}
