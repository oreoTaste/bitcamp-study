package com.eomcs.lms.service;

import java.util.List;
import com.eomcs.lms.domain.Member;

public interface MemberService {

  List<Member> list() throws Exception;

  List<Member> search(String keyword) throws Exception;

  Member get(int memberNo) throws Exception;

  boolean update(Member member) throws Exception;

  boolean delete(int memberNo) throws Exception;

  boolean add(Member member) throws Exception;

  Member get(String email, String password) throws Exception;

  Member get(String email) throws Exception;

}
