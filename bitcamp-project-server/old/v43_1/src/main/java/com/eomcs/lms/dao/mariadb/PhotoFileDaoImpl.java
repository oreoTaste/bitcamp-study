package com.eomcs.lms.dao.mariadb;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.PhotoFile;

public class PhotoFileDaoImpl implements PhotoFileDao {
  SqlSessionFactory sqlSessionFactory;

  public PhotoFileDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public int insert(PhotoFile photoFile) throws Exception {
    try(SqlSession sqlSession = sqlSessionFactory.openSession()){
      int result = sqlSession.insert("PhotoFileMapper.insert", photoFile);
      sqlSession.commit();
      return result;
    }
  }

  // select photo_file_id, photo_id, file_path from lms_photo_file where photo_id = 1 order by photo_file_id asc;
  @Override
  public List<PhotoFile> findAll(int boardNo) throws Exception {
    try(SqlSession sqlSession = sqlSessionFactory.openSession()){

      return sqlSession.selectList("PhotoFileMapper.findAll", boardNo);
    }
  }

  @Override
  public int deleteAll(int boardNo) throws Exception {
    try(SqlSession sqlSession = sqlSessionFactory.openSession()){

      int result = sqlSession.delete("PhotoFileMapper.deleteAll", boardNo);
      sqlSession.commit();
      return result;
    }
  }


}
