package com.eomcs.lms.dao;

import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.domain.Member;

public class MemberObjectFileDao extends AbstractObjectFileDao<Member> {

  String fileName;
  List<Member> list;

  public MemberObjectFileDao(String fileName) {
    super(fileName);
    list = new ArrayList<>();
    this.fileName = fileName;
    loadData();
  }

  // 서블릭 객체들이 데이터를 다룰 때 사용할 메서드를 정의한다.
  public int insert(Member member) throws Exception {

    Member originMember = findByNo(member.getNo());

    if (originMember != null) // 같은 번호의 게시물이 있다면,
      return 0;
    list.add(member); // 새 게시물을 등록한다.
    saveData();
    return 1;
  }

  public List<Member> findAll() throws Exception {
    return list;
  }

  public Member findByNo(int no) throws Exception {
    int index = indexOf(no);
    if(index == -1)
      return null;
    return list.get(index);
  }
  
  public int delete(int no) throws Exception {
    
    for(int i = 0; i < list.size(); i++) {
      if(list.get(i).getNo() == no) {
        list.remove(i);
        saveData();
        return 1;
      }
    } return 0;
  }
  
  public int update(Member member) throws Exception {
    for(int i = 0; i < list.size(); i++) {
      if(list.get(i).getNo() == member.getNo()) {
        list.set(i, member);
        saveData();
        return 1;
      }
    } return 0;
  }
  
  @Override
  protected <K> int indexOf(K key) {
    for(int i = 0; i < list.size(); i++) {
      if(list.get(i).getNo() == (int) key)
        return i;
    } return -1;
  }


}
