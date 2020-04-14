package com.eomcs.lms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.eomcs.lms.service.BoardService;
import com.eomcs.lms.util.RequestMapping;

@Component
public class BoardDeleteController {

  @Autowired
  BoardService boardService;

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


}
