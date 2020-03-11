package com.eomcs.lms.dao.mariadb;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

public class LessonDaoImpl implements LessonDao {
  SqlSessionFactory sqlSessionFactory;

  public LessonDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public List<Lesson> findByKeyword(Map<String, Object> keywordParams) throws Exception {
    try(SqlSession sqlSession = sqlSessionFactory.openSession()){
      return sqlSession.selectList("LessonMapper.findByKeyword", keywordParams);
    }
  }
  
  @Override
  public int insert(Lesson lesson) throws Exception {
    try(SqlSession sqlSession = sqlSessionFactory.openSession()){
      int result = sqlSession.insert("LessonMapper.insertLesson", lesson);
      return result;
    }
  }

  @Override
  public List<Lesson> findAll() throws Exception {
    try(SqlSession sqlSession = sqlSessionFactory.openSession()){
      return sqlSession.selectList("LessonMapper.selectLesson");
    }
  }

  @Override
  public Lesson findByNo(int no) throws Exception {
    try(SqlSession sqlSession = sqlSessionFactory.openSession()){
      return sqlSession.selectOne("LessonMapper.detailLesson", no);
    }
  }

  @Override
  public int update(Lesson lesson) throws Exception {
    try(SqlSession sqlSession = sqlSessionFactory.openSession()){
      int result = sqlSession.update("LessonMapper.updateLesson", lesson);
      return result;
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try(SqlSession sqlSession = sqlSessionFactory.openSession()){
      int result = sqlSession.delete("LessonMapper.deleteLesson", no);
      return result;
    }
  }

}
