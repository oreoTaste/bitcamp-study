package com.eomcs.lms.dao.proxy;

import java.util.List;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

public class LessonDaoProxy implements LessonDao {

  DaoProxyHelper daoProxyHelper;

  public LessonDaoProxy(DaoProxyHelper daoProxyHelper) {
    this.daoProxyHelper = daoProxyHelper;
  }

  @Override
  public int insert(Lesson Lesson) throws Exception {
    
    return (int) daoProxyHelper.request((in,out) -> {
        out.writeUTF("/lesson/add");
        out.writeObject(Lesson);
        out.flush();

        String response = in.readUTF();
        if (response.equals("FAIL"))
          throw new Exception(in.readUTF());

        System.out.println("저장하였습니다.");
        return 1;
    });
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Lesson> findAll() throws Exception {

    return (List<Lesson>)daoProxyHelper.request((in, out) -> {
        out.writeUTF("/lesson/list");
        out.flush();

        String response = in.readUTF();
        if (response.equals("FAIL"))
          throw new Exception(in.readUTF());

        return in.readObject();
    });
    
  }

  @Override
  public Lesson findByNo(int no) throws Exception {
    
    return (Lesson) daoProxyHelper.request((in, out) -> {
        out.writeUTF("/lesson/detail");
        out.writeInt(no);
        out.flush();

        String response = in.readUTF();
        if (response.equals("FAIL"))
          throw new Exception(in.readUTF());
 
        return in.readObject();
    });
  }

  @Override
  public int update(Lesson Lesson) throws Exception {
    
    return (int) daoProxyHelper.request((in, out) -> {
        out.writeUTF("/lesson/update");
        out.writeObject(Lesson);
        out.flush();

        String response = in.readUTF();
        if (response.equals("FAIL"))
          throw new Exception(in.readUTF());

        return 1;
    });
    
  }

  @Override
  public int delete(int no) throws Exception {
    
    return (int) daoProxyHelper.request((in, out) -> {
        out.writeUTF("/Lesson/delete");
        out.writeInt(no);
        out.flush();

        String response = in.readUTF();
        if (response.equals("FAIL"))
          throw new Exception(in.readUTF());

        return 1;
    });
  }

}
