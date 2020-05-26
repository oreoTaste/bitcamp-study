package com.eomcs.lms.service.impl;

import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.service.PhotoBoardService;

@Component
public class PhotoBoardServiceImpl implements PhotoBoardService {
  TransactionTemplate transactionTemplate;
  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;

  public PhotoBoardServiceImpl(PlatformTransactionManager txManager,
      PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao) {
    this.transactionTemplate = new TransactionTemplate(txManager);
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }

  @Transactional
  // 메소드 전체를 Transaction으로 묶는다.
  // 예외 없으면 : 자동 commit()
  // 예외 있으면 : rollback;
  @Override
  public boolean add(PhotoBoard photoBoard) throws Exception {

    if (photoBoardDao.insert(photoBoard) == 0)
      throw new Exception("사진 게시글 등록에 실패했습니다.");

    return photoFileDao.insert(photoBoard) > 0;
  }

  @Transactional
  @Override
  public List<PhotoBoard> listLessonPhoto(int lessonNo) throws Exception {
    return photoBoardDao.findAllByLessonNo(lessonNo);
  }

  @Transactional
  @Override
  public PhotoBoard get(int photoNo) throws Exception {
    return photoBoardDao.findByNo(photoNo);
  }

  @Transactional
  @Override
  public boolean update(PhotoBoard photoBoard) throws Exception {
    if(photoBoardDao.update(photoBoard) == 0)
      throw new Exception("사진 게시글 등록에 실패했습니다.");
    if(photoBoard.getFiles() != null) {
      photoFileDao.deleteAll(photoBoard.getNo());
      return photoFileDao.insert(photoBoard) > 0;
    }
    return true;
  }

  @Transactional
  @Override
  public boolean delete(int photoFileNo) throws Exception {
    return photoFileDao.deleteAll(photoFileNo) > 0;
//    if(photoBoardDao.delete(photoFileNo) == 0)
//      throw new Exception("해당 번호의 사진 게시글이 없습니다.");
  }

  /*
  public List<PhotoFile> listPhotoFile(int lessonNo) throws Exception {
    return photoFileDao.findAll(lessonNo);
  }
   */



}
