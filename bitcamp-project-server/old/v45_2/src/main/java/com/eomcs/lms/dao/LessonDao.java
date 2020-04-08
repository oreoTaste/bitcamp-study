package com.eomcs.lms.dao;

import java.util.List;
import java.util.Map;
import com.eomcs.lms.domain.Lesson;

public interface LessonDao {

  int insert(Lesson lesson) throws Exception;

  List<Lesson> findAll() throws Exception;

  Lesson findByNo(int no) throws Exception;

  int update(Lesson lesson) throws Exception;
 
  int delete(int no) throws Exception;

  default List<Lesson> findByKeyword(Map<String, Object> keywordParams) throws Exception{
    return null;}
}
