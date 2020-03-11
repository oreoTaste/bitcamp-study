package com.eomcs.lms.dao.mariadb;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

public class MemberDaoImpl implements MemberDao {
  SqlSessionFactory sqlSessionFactory;

  public MemberDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public int insert(Member member) throws Exception {
    try(SqlSession sqlSession = sqlSessionFactory.openSession()){
      
      int result = sqlSession.insert("MemberMapper.insertMember", member);
      sqlSession.commit();
      return result;
    }
  }

  @Override
  public List<Member> findAll() throws Exception {
    try(SqlSession sqlSession = sqlSessionFactory.openSession()){
      
      return sqlSession.selectList("MemberMapper.selectMember");
    }
  }

  @Override
  public Member findByNo(int no) throws Exception {
    try(SqlSession sqlSession = sqlSessionFactory.openSession()){

      return sqlSession.selectOne("MemberMapper.findByNo", no);
    }
  }

  @Override
  public int update(Member member) throws Exception {
    try(SqlSession sqlSession = sqlSessionFactory.openSession()){

      int result = sqlSession.update("MemberMapper.updateMember", member);
      sqlSession.commit();
      return result;
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try(SqlSession sqlSession = sqlSessionFactory.openSession()){
      int result = sqlSession.delete("MemberMapper.deleteMember", no);
      sqlSession.commit();
      return result;
    }
  }


  @Override
  public List<Member> findByKeyword(String keyword) throws Exception {
    try(SqlSession sqlSession = sqlSessionFactory.openSession()){

      return sqlSession.selectList("MemberMapper.findByKeyword", keyword);
    }
  }

  @Override
  public Member findByEmailAndPassword(String email, String password) throws Exception {
    try(SqlSession sqlSession = sqlSessionFactory.openSession()){
      HashMap<String, Object> map = new HashMap<>();
      map.put("email", email);
      map.put("password", password);
      return sqlSession.selectOne("MemberMapper.findByEmailAndPassword", map);
    }
  }

}
