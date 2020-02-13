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

public abstract class AbstractObjectFileDao<T> {

  protected String fileName;
  protected List<T> list;
 
  public AbstractObjectFileDao(String fileName) {
    this.fileName = fileName;
    list = new ArrayList<>();
    loadData();
  }

  @SuppressWarnings("unchecked")
  public void loadData() {
    File file = new File(fileName);

    try (ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(new FileInputStream(file)))) {

      list = (List<T>) in.readObject();

      System.out.printf("총 %d개 객체 로딩완료\n", list.size());

    } catch (Exception e) {
      System.out.println("로딩 실패 : " + e.getMessage());
    }
  }

  public void saveData() {
    File file = new File(fileName);

    try (ObjectOutputStream out = new ObjectOutputStream(
        new BufferedOutputStream(new FileOutputStream(file)))) {

      // 기존 직렬화(serialized) 데이터를 초기화 시킨 후, 다시 직렬화 수행한다.
      out.reset();
      out.writeObject(list);

      System.out.printf("총 %d개 객체 저장완료\n", list.size());
    } catch (IOException e) {
      System.out.println("저장 실패 : " + e.getMessage());
    }
  }
  
  abstract <K> int indexOf(K id);
  
}
