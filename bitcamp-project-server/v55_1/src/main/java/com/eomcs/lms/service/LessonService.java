package com.eomcs.lms.service;

import java.util.HashMap;
import java.util.List;
import com.eomcs.lms.domain.Lesson;

public interface LessonService {
  Lesson get(int lessonNo) throws Exception;

  boolean add(Lesson lesson) throws Exception;

  boolean delete(int no) throws Exception;

  List<Lesson> list() throws Exception;

  boolean update(Lesson newLesson) throws Exception;

  List<Lesson> search(HashMap<String, Object> map) throws Exception;
  
  
  
}
