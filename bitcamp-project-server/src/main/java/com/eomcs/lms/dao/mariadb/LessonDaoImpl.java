package com.eomcs.lms.dao.mariadb;

import java.util.List;
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
  public int insert(Lesson lesson) throws Exception {
    try(SqlSession sqlSession = sqlSessionFactory.openSession()){
      int result = sqlSession.insert("LessonMapper.insertLesson", lesson);
      sqlSession.commit();
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
      sqlSession.commit();
      return result;
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try(SqlSession sqlSession = sqlSessionFactory.openSession()){
      int result = sqlSession.delete("LessonMapper.deleteLesson", no);
      sqlSession.commit();
      return result;
    }
  }

}
