package com.eomcs.lms.service;

import java.util.List;
import com.eomcs.lms.domain.PhotoBoard;

public interface PhotoBoardService {
  boolean add(PhotoBoard photoBoard) throws Exception;

  List<PhotoBoard> listLessonPhoto(int lessonNo) throws Exception;
  
  PhotoBoard get(int photoNo) throws Exception;

  boolean update(PhotoBoard photoBoard) throws Exception;
  
  boolean delete(int photoFileNo) throws Exception;
  
}
