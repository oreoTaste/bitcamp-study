package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import com.eomcs.lms.domain.Lesson;

public class LessonListServlet implements Servlet {

  List<Lesson> lessons;
  
  public LessonListServlet(List<Lesson> lessons) {
    this.lessons = lessons;
  }
  
  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws IOException {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(lessons);
  }

}
