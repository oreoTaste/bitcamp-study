package com.eomcs.lms.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.service.BoardService;
import com.eomcs.lms.util.RequestMapping;

@Component("/board/list")
public class BoardListController {

  @Autowired
  BoardService boardService;

  @RequestMapping("/board/list")
  public String list(HttpServletRequest request, HttpServletResponse response) throws Exception{

    List<Board> boards = boardService.list();

    request.setAttribute("list", boards);
    response.setContentType("text/html;charset=UTF-8");
    return "/board/list.jsp";
  }

}















