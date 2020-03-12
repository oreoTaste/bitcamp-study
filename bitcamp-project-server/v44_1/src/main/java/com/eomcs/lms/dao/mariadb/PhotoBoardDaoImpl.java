package com.eomcs.lms.dao.mariadb;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.domain.PhotoBoard;

public class PhotoBoardDaoImpl implements PhotoBoardDao {
  SqlSessionFactory sqlSessionFactory;

  public PhotoBoardDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public int insert(PhotoBoard photoBoard) throws Exception {
    try(SqlSession sqlSession = sqlSessionFactory.openSession()){
      
      int count = sqlSession.insert("PhotoBoardMapper.insertPhotoBoard", photoBoard);
      return count;
    }
  }

  @Override
  public List<PhotoBoard> findAllByLessonNo(int lessonNo) throws Exception {
    try(SqlSession sqlSession = sqlSessionFactory.openSession()){
      
      return sqlSession.selectList("PhotoBoardMapper.findAllByLessonNo", lessonNo);
    }
  }

  @Override
  public PhotoBoard findByNo(int no) throws Exception {
    try(SqlSession sqlSession = sqlSessionFactory.openSession()){

      return sqlSession.selectOne("PhotoBoardMapper.findByNo", no);
    }
  }

  @Override
  public int update(PhotoBoard photoBoard) throws Exception {
    try(SqlSession sqlSession = sqlSessionFactory.openSession()){
      int result = sqlSession.update("PhotoBoardMapper.updatePhotoBoard", photoBoard);
      return result;
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try(SqlSession sqlSession = sqlSessionFactory.openSession()){
      
      int result = sqlSession.delete("PhotoBoardMapper.deletePhotoBoard", no);
      return result;
    }
  }

}
