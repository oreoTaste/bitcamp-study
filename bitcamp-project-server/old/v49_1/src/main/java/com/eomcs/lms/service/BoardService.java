package com.eomcs.lms.service;

import java.util.List;
import com.eomcs.lms.domain.Board;

public interface BoardService {

  boolean add(Board board) throws Exception;

  boolean delete(int boardNo) throws Exception;
  
  Board get(int boardNo) throws Exception;

  List<Board> list() throws Exception;

  boolean update(Board newBoard) throws Exception;
}
