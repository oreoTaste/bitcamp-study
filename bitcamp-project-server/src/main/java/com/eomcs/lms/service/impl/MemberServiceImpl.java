package com.eomcs.lms.service.impl;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Component;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.service.MemberService;

@Component
public class MemberServiceImpl implements MemberService {
  MemberDao memberDao;
  
  public MemberServiceImpl(MemberDao memberDao) {
    this.memberDao = memberDao;
  }
  
  @Override
  public List<Member> list() throws Exception {
    return memberDao.findAll();
  }

  @Override
  public List<Member> search(String keyword) throws Exception {
    return memberDao.findByKeyword(keyword);
  }

  @Override
  public Member get(int memberNo) throws Exception {
    return memberDao.findByNo(memberNo);
  }

  @Override
  public boolean update(Member member) throws Exception {
    return memberDao.update(member) > 0;
  }

  @Override
  public boolean delete(int memberNo) throws Exception {
    return memberDao.delete(memberNo) > 0;
  }

  @Override
  public boolean add(Member member) throws Exception {
    return memberDao.insert(member) > 0;
  }

  @Override
  public Member get(String email, String password) throws Exception {
    HashMap<String, Object> map = new HashMap<>();
    map.put("email", email);
    map.put("password", password);
    return memberDao.findByEmailAndPassword(map);
  }

}
