package com.eomcs.lms.dao.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

public class MemberDaoProxy implements MemberDao {

  DaoProxyHelper daoProxyHelper;
  
  public MemberDaoProxy(DaoProxyHelper daoProxyHelper) {
    this.daoProxyHelper = daoProxyHelper;
  }

  @Override
  public int insert(Member member) throws Exception {
    
    return (int) daoProxyHelper.request(new MemberInsertWorker(member));
    
    /*
    return (int) daoProxyHelper.request((in, out) -> {
      out.writeUTF("/member/add");
      out.writeObject(member);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL"))
        throw new Exception(in.readUTF());

      System.out.println("저장하였습니다.");
      return 1;
    });
    */
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Member> findAll() throws Exception {
    
    return (List<Member>) daoProxyHelper.request((in, out) -> {
      out.writeUTF("/member/list");
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL"))
        throw new Exception(in.readUTF());

      return in.readObject();
    });
  }

  @Override
  public Member findByNo(int no) throws Exception {
    
    return (Member) daoProxyHelper.request((in,out) -> {
      out.writeUTF("/member/detail");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL"))
        throw new Exception(in.readUTF());

      return in.readObject();
    });
  }

  @Override
  public int update(Member Member) throws Exception {
    
    return (int) daoProxyHelper.request(new Worker() {

      @Override
      public Object execute(ObjectInputStream in, ObjectOutputStream out) throws Exception {
        out.writeUTF("/member/update");
        out.writeObject(Member);
        out.flush();

        String response = in.readUTF();
        if (response.equals("FAIL"))
          throw new Exception(in.readUTF());

        return 1;
      }
    });
  }

  @Override
  public int delete(int no) throws Exception {
    
    return (int) daoProxyHelper.request((in, out) -> {
      out.writeUTF("/member/delete");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL"))
        throw new Exception(in.readUTF());

      return 1;
    });
  }

}
