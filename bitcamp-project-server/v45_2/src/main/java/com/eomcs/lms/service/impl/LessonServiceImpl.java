package com.eomcs.lms.service.impl;

import java.util.HashMap;
import java.util.List;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.service.LessonService;

public class LessonServiceImpl implements LessonService {
  LessonDao lessonDao;
  
  public LessonServiceImpl(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }
  
  @Override
  public Lesson get(int lessonNo) throws Exception {
    return lessonDao.findByNo(lessonNo);
  }

  @Override
  public boolean add(Lesson lesson) throws Exception {
    return lessonDao.insert(lesson) > 0;
  }

  @Override
  public boolean delete(int no) throws Exception {
    return lessonDao.delete(no) > 0;
  }

  @Override
  public List<Lesson> list() throws Exception {
    return lessonDao.findAll();
  }

  @Override
  public void update(Lesson newLesson) throws Exception {
    lessonDao.update(newLesson);
  }

  @Override
  public List<Lesson> search(HashMap<String, Object> map) throws Exception {
    return lessonDao.findByKeyword(map);
  }
}
