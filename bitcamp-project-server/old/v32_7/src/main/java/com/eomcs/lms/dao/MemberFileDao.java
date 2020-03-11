package com.eomcs.lms.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.domain.Member;

public class MemberFileDao {

  String fileName;
  List<Member> list;

  public MemberFileDao(String fileName) {
    list = new ArrayList<>();
    this.fileName = fileName;
    loadData();
  }


  @SuppressWarnings("unchecked")
  private void loadData() {
    File file = new File(fileName);

    try (ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(new FileInputStream(file)))) {

      list = (List<Member>) in.readObject();

      System.out.printf("총 %d개 게시글 로딩완료\n", list.size());

    } catch (Exception e) {
      System.out.println("로딩 실패 : " + e.getMessage());
    }
  }

  private void saveData() {
    File file = new File(fileName);

    try (ObjectOutputStream out = new ObjectOutputStream(
        new BufferedOutputStream(new FileOutputStream(file)))) {

      // 기존 직렬화(serialized) 데이터를 초기화 시킨 후, 다시 직렬화 수행한다.
      out.reset();
      out.writeObject(list);

      System.out.printf("총 %d개 게시글 저장완료\n", list.size());
    } catch (IOException e) {
      System.out.println("저장 실패 : " + e.getMessage());
    }
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
    for(Member b : list) {
      if(b.getNo() == no)
        return b;
    }
    return null;
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

  public int delete(int no) throws Exception {
    
    for(int i = 0; i < list.size(); i++) {
      if(list.get(i).getNo() == no) {
        list.remove(i);
        saveData();
        return 1;
      }
    } return 0;
  }



}
