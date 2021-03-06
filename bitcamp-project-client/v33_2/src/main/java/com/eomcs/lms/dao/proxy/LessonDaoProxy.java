package com.eomcs.lms.dao.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

public class LessonDaoProxy implements LessonDao {

  String host;
  int port;

  public LessonDaoProxy(String host, int port) {
    this.host = host;
    this.port = port;
  }

  @Override
  public int insert(Lesson Lesson) throws Exception {
    try(Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())){
      out.writeUTF("/lesson/add");
      out.writeObject(Lesson);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL"))
        throw new Exception(in.readUTF());

      System.out.println("저장하였습니다.");
      return 1;
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Lesson> findAll() throws Exception {

    try(Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())){
      out.writeUTF("/lesson/list");
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL"))
        throw new Exception(in.readUTF());

      return (List<Lesson>) in.readObject();
    }
  }

  @Override
  public Lesson findByNo(int no) throws Exception {
    try(Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())){
      out.writeUTF("/lesson/detail");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL"))
        throw new Exception(in.readUTF());

      return (Lesson) in.readObject();
    }
  }

  @Override
  public int update(Lesson Lesson) throws Exception {
    try(Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())){
      out.writeUTF("/lesson/update");
      out.writeObject(Lesson);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL"))
        throw new Exception(in.readUTF());

      return 1;
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try(Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())){
      out.writeUTF("/Lesson/delete");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL"))
        throw new Exception(in.readUTF());

      return 1;
    }
  }

}
