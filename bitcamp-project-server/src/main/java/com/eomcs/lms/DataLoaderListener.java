package com.eomcs.lms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import com.eomcs.lms.context.ApplicationContextListener;
import com.eomcs.lms.dao.json.BoardJsonFileDao;
import com.eomcs.lms.dao.json.LessonJsonFileDao;
import com.eomcs.lms.dao.json.MemberJsonFileDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.Member;

public class DataLoaderListener implements ApplicationContextListener {
  
  List<Lesson> lessonList = new ArrayList<>();
  List<Member> memberList = new LinkedList<>();

  @Override
  public void contextInitialized(Map<String, Object> context) {
    System.out.println("데이터를 로딩합니다.");
    loadLessonData();
    loadMemberData();
    // 애플리케이션의 데이터를 처리할 객체를 준비한다.
    // BoardObjectFileDao boardDao = new BoardObjectFileDao("./board.ser2");
    BoardJsonFileDao boardDao = new BoardJsonFileDao("./board.json");
    LessonJsonFileDao lessonDao = new LessonJsonFileDao("./lesson.json");
    MemberJsonFileDao memberDao = new MemberJsonFileDao("./member.json");
    
    // 이 메서드를 호출한 쪽(App)에서 Dao객체를 사용할 수 있도록 Map에 담아둔다.
    context.put("boardDao", boardDao);
    context.put("lessonDao", lessonDao);
    context.put("memberDao", memberDao);
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    System.out.println("데이터를 저장합니다.");
    saveLessonData();
    saveMemberData();

  }
  

  @SuppressWarnings("unchecked")
  private void loadLessonData() {
    File file = new File("./lesson.ser2");
    try (ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(new FileInputStream(file)))) {
      
      lessonList = (List<Lesson>) in.readObject();
      
      System.out.printf("총 %d개 레슨 로딩완료\n", lessonList.size());
    } catch(Exception e) {
      System.out.println("로딩 실패 : " + e.getMessage());
    }
  }

  private void saveLessonData() {
    File file = new File("./lesson.ser2");

    try (ObjectOutputStream out = new ObjectOutputStream(
        new BufferedOutputStream(new FileOutputStream(file)));) {
      
      out.writeObject(lessonList);
      System.out.printf("총 %d개 레슨 저장완료\n", lessonList.size());
    } catch(IOException e) {
      System.out.println("저장 실패 : " + e.getMessage());
    }

  }


  @SuppressWarnings("unchecked")
  private void loadMemberData() {
    File file = new File("./member.ser2");

    try (ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(new FileInputStream(file)))) {
      
      memberList = (List<Member>) in.readObject();
      
      System.out.printf("총 %d개 멤버 로딩완료\n", memberList.size());

    } catch(Exception e) {
      System.out.println("로딩 실패 : " + e.getMessage());
    }
  }

  private void saveMemberData() {

    File file = new File("./member.ser2");

    try (ObjectOutputStream out = new ObjectOutputStream(
        new BufferedOutputStream(new FileOutputStream(file)))) {
      
      out.writeObject(memberList);
      
      System.out.printf("총 %d개 멤버 저장완료\n", memberList.size());

    }catch (IOException e) {
      System.out.println("저장 실패 : " + e.getMessage());
    }
  }

}
