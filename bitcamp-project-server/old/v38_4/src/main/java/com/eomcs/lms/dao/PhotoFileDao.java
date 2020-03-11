package com.eomcs.lms.dao;

import java.util.List;
import com.eomcs.lms.domain.PhotoFile;

public interface PhotoFileDao {
  
  int insert(PhotoFile photoFile) throws Exception;

  List<PhotoFile> findAll(int boardNo) throws Exception;
  
  /*
  public PhotoFile findByNo(int no) throws Exception;

  public int update(PhotoFile photoFile) throws Exception;
   */

  int deleteAll(int boardNo) throws Exception;


}
