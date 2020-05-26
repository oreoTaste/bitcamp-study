package com.eomcs.lms.web;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
  static Logger logger = LogManager.getLogger(BoardController.class);
  
  @Autowired
  BoardService boardService;
  
  public BoardController() {
    logger.debug("boardController 생성!!!");
  }
  

  // 얘가 request Handler 메서드임
  @GetMapping("form")
  public void form() throws Exception {}

  // 얘가 request Handler 메서드임
  @PostMapping("add")
  public String add(Board board) throws Exception {
    if(boardService.add(board))
      return "redirect:list";
    else
      throw new Exception("추가할 게시물 정보가 유효하지 않습니다.");
  }

  // 얘가 request Handler 메서드임
  @GetMapping("delete")
  public String delete(int no) throws Exception {

    if(boardService.delete(no))
      return "redirect:list";
    else
      throw new Exception("삭제할 게시물 정보가 유효하지 않습니다.");
  }

  // 얘가 request Handler 메서드임
  @GetMapping("detail")
  public void detail(int no, Model model) throws Exception {
    Board board = boardService.get(no);
    model.addAttribute("board", board);
  }

  // 얘가 request Handler 메서드임
  @GetMapping("list")
  public void list(Model model) throws Exception{
    List<Board> boards = boardService.list();
    model.addAttribute("list", boards);
  }

  // 얘가 request Handler 메서드임
  @GetMapping("updateForm")
  public void updateForm(int no, Model model) throws Exception {
    model.addAttribute("board", boardService.get(no));
  }

  // 얘가 request Handler 메서드임
  @PostMapping("update")
  public String update(Board board) throws Exception {
    if(boardService.update(board))
      return "redirect:list";
    else
      throw new Exception("수정할 게시물 정보가 유효하지 않습니다.");
  }



}
