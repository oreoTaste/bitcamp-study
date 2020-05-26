package com.eomcs.lms.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.eomcs.lms.domain.Member;

public interface MemberDao {

  int insert(Member member) throws Exception;

  List<Member> findAll() throws Exception;
  
  Member findByNo(int no) throws Exception;
  
  int delete(int no) throws Exception;
  
  int update(Member member) throws Exception;
  
  List<Member> findByKeyword(String keyword) throws Exception;
  
  Member findByEmailAndPassword(Map<String, Object> map) throws Exception;

  Member findByEmail(HashMap<String, Object> map) throws Exception;

}
