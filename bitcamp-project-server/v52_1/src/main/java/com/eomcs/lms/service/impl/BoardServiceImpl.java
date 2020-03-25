package com.eomcs.lms.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService {
  BoardDao boardDao;
  
  public BoardServiceImpl(BoardDao boardDao) {
    this.boardDao = boardDao;
  }
  
  @Override
  public boolean add(Board board) throws Exception {
    return boardDao.insert(board) > 0;
  }

  @Override
  public boolean delete(int boardNo) throws Exception {
    return boardDao.delete(boardNo) > 0;
  }

  @Override
  public Board get(int boardNo) throws Exception {
    return boardDao.findByNo(boardNo);
  }

  @Override
  public List<Board> list() throws Exception {
    return boardDao.findAll();
  }

  @Override
  public boolean update(Board board) throws Exception {
    return boardDao.update(board) > 0;
  }
  
  

}
