package com.eomcs.lms.dao;

import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.domain.Lesson;

public class LessonObjectFileDao extends AbstractObjectFileDao<Lesson> implements LessonDao {

  String fileName;
  List<Lesson> list;

  public LessonObjectFileDao(String fileName) {
    super(fileName);
    list = new ArrayList<>();
    this.fileName = fileName;
    loadData();
  }
  
  // 서블릭 객체들이 데이터를 다룰 때 사용할 메서드를 정의한다.
  @Override
  public int insert(Lesson lesson) throws Exception {

    Lesson originLesson = findByNo(lesson.getNo());

    if (originLesson != null) // 같은 번호의 게시물이 있다면,
      return 0;
    list.add(lesson); // 새 게시물을 등록한다.
    saveData();
    return 1;
  }


  @Override
  public List<Lesson> findAll() throws Exception {
    return list;
  }

  @Override
  public Lesson findByNo(int no) throws Exception {
    for(Lesson b : list) {
      if(b.getNo() == no)
        return b;
    }
    return null;
  }

  @Override
  public int update(Lesson lesson) throws Exception {
    for(int i = 0; i < list.size(); i++) {
      if(list.get(i).getNo() == lesson.getNo()) {
        list.set(i, lesson);
        saveData();
        return 1;
      }
    } return 0;
  }
 
  @Override
  public int delete(int no) throws Exception {
    
    for(int i = 0; i < list.size(); i++) {
      if(list.get(i).getNo() == no) {
        list.remove(i);
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
