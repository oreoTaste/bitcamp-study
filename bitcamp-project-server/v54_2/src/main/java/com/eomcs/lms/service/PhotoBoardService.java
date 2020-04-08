package com.eomcs.lms.service;

import java.util.List;
import com.eomcs.lms.domain.PhotoBoard;

public interface PhotoBoardService {
  void add(PhotoBoard photoBoard) throws Exception;

  List<PhotoBoard> listLessonPhoto(int lessonNo) throws Exception;
  
  PhotoBoard get(int photoNo) throws Exception;

  void update(PhotoBoard photoBoard) throws Exception;
  
  void delete(int photoFileNo) throws Exception;
  
}
