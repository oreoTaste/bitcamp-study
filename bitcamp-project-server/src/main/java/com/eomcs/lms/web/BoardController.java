package com.eomcs.lms.web;

import java.sql.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.service.BoardService;
import com.eomcs.lms.util.RequestMapping;

@Component
public class BoardController {

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

  @RequestMapping("/board/delete")
  public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {

    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    int no = Integer.parseInt(request.getParameter("no"));
    if(boardService.delete(no))
      return "redirect:list";
    else
      throw new Exception("삭제할 게시물 정보가 유효하지 않습니다.");
  }

  @RequestMapping("/board/detail")
  public String detail(HttpServletRequest request, HttpServletResponse response) throws Exception {
      int no = Integer.parseInt(request.getParameter("no"));
      Board board = boardService.get(no);
      
      request.setAttribute("board", board);
      response.setContentType("text/html;charset=UTF-8");
      return "/board/detail.jsp";
  }
  
  @RequestMapping("/board/list")
  public String list(HttpServletRequest request, HttpServletResponse response) throws Exception{

    List<Board> boards = boardService.list();

    request.setAttribute("list", boards);
    response.setContentType("text/html;charset=UTF-8");
    return "/board/list.jsp";
  }
  
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
