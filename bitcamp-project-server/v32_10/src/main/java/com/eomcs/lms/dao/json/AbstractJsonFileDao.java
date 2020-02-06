package com.eomcs.lms.dao.json;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

public abstract class AbstractJsonFileDao<T> {

  protected String fileName;
  protected List<T> list;
 
  public AbstractJsonFileDao(String fileName) {
    this.fileName = fileName;
    list = new ArrayList<>();
    loadData();
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  void loadData() {
    File file = new File(fileName);

    try (BufferedReader in = new BufferedReader(new FileReader(file))) {

      // 현재 클래스의 정보를 알아낸다.
      Class<?> currType = this.getClass();
      System.out.println(currType);
      
      Type parentType = currType.getGenericSuperclass();
      System.out.println(parentType);
      
      ParameterizedType parentType2 = (ParameterizedType) parentType;
      
      Type[] typeParams = parentType2.getActualTypeArguments();
      
      Type itemType = typeParams[0];
      System.out.println(itemType);
      
      T[] arr = (T[]) Array.newInstance((Class)itemType, 0);
      
      T[] dataArr = (T[])new Gson().fromJson(in, arr.getClass());
      for(T b : dataArr) {
        list.add(b);
      }

      System.out.printf("총 %d개 객체 로딩완료\n", list.size());

    } catch (Exception e) {
      System.out.println("로딩 실패 : " + e.getMessage());
    }
  }

  void saveData() {
    File file = new File(fileName);

    try (BufferedWriter out = new BufferedWriter(new FileWriter(file))) {

      out.write(new Gson().toJson(list));
      

      System.out.printf("총 %d개 객체 저장완료\n", list.size());
    } catch (IOException e) {
      System.out.println("저장 실패 : " + e.getMessage());
    }
  }
  
  abstract <K> int indexOf(K id);
  
}
