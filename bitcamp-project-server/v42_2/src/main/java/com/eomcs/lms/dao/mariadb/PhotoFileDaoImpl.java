package com.eomcs.lms.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.PhotoFile;
import com.eomcs.sql.DataSource;

public class PhotoFileDaoImpl implements PhotoFileDao {
  DataSource dataSource;
  
  public PhotoFileDaoImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public int insert(PhotoFile photoFile) throws Exception {
    try(Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "INSERT INTO lms_photo_file(photo_id, file_path) values(?, ?)")) {

      stmt.setInt(1, photoFile.getBoardNo());
      stmt.setString(1, photoFile.getFilePath());
      return stmt.executeUpdate();
    }
  }

// select photo_file_id, photo_id, file_path from lms_photo_file where photo_id = 1 order by photo_file_id asc;
  @Override
  public List<PhotoFile> findAll(int boardNo) throws Exception {
    try(Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "select photo_file_id, photo_id, file_path"
                + " from lms_photo_file"
                + " where photo_id = ?"
                + " order by photo_file_id asc")){
      
      stmt.setInt(1, boardNo);
      ResultSet rs = stmt.executeQuery();

      ArrayList<PhotoFile> list = new ArrayList<>();
      while(rs.next()) {
        
//        생성자를 통해 인스턴스 필드 값 설정하기
//        list.add(new PhotoFile(
//            rs.getInt("photo_file_id"),
//            rs.getString("file_path"),
//            rs.getInt("photo_id")));
        
//        세터를 통해 체인방식으로 인스턴스 필드 값 설정하기
        list.add(new PhotoFile()
            .setNo(rs.getInt("photo_file_id"))
            .setFilePath(rs.getString("file_path"))
            .setBoardNo(rs.getInt("photo_id")));
        
      }
      return list;
    }
  }
  
  @Override
  public int deleteAll(int boardNo) throws Exception {
    try(Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "DELETE FROM lms_photo_file WHERE photo_id = ?")) {
      
      stmt.setInt(1, boardNo);

      return stmt.executeUpdate();
    }
  }


}
