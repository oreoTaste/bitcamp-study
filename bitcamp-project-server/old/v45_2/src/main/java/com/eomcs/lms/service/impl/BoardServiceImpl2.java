package com.eomcs.lms.service.impl;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.service.BoardService;

public class BoardServiceImpl2 implements BoardService {
  SqlSessionFactory sqlSessionFactory;

  public BoardServiceImpl2(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public boolean add(Board board) throws Exception {

    try(SqlSession sqlSession = sqlSessionFactory.openSession()){
      BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
      return boardDao.insert(board) > 0;
    }
  }

  @Override
  public boolean delete(int boardNo) throws Exception {
    try(SqlSession sqlSession = sqlSessionFactory.openSession()){
      BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
      return boardDao.delete(boardNo) > 0;
    }
  }

  @Override
  public Board get(int boardNo) throws Exception {
    try(SqlSession sqlSession = sqlSessionFactory.openSession()){
      BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
      return boardDao.findByNo(boardNo);
    }
  }

  @Override
  public List<Board> list() throws Exception {
    try(SqlSession sqlSession = sqlSessionFactory.openSession()){
      BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
      return boardDao.findAll();
    }
  }

  @Override
  public boolean update(Board board) throws Exception {
    try(SqlSession sqlSession = sqlSessionFactory.openSession()){
      BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
      return boardDao.update(board) > 0;
    }
  }


}
