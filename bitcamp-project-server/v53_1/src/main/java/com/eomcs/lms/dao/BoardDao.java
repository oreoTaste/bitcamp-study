package com.eomcs.lms.dao;

import java.util.List;
import com.eomcs.lms.domain.Board;

// Dao 사용법 통일!!
// @Repository (model)
public interface BoardDao {
  
  public int insert(Board board) throws Exception;

  public List<Board> findAll() throws Exception;

  public Board findByNo(int no) throws Exception;

  public int update(Board board) throws Exception;

  public int delete(int no) throws Exception;



}
