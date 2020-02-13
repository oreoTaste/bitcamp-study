package com.eomcs.lms.dao.proxy;

import java.util.List;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

public class BoardDaoProxy implements BoardDao {

  DaoProxyHelper daoProxyHelper;

  public BoardDaoProxy(DaoProxyHelper helper) {
    this.daoProxyHelper = helper;
  }

  @Override
  public int insert(Board board) throws Exception {

    return (int) daoProxyHelper.request((in, out) ->{
        out.writeUTF("/board/add");
        out.writeObject(board);
        out.flush();

        String response = in.readUTF();
        if (response.equals("FAIL"))
          throw new Exception(in.readUTF());

        return 1;
    });
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Board> findAll() throws Exception {

    return (List<Board>) daoProxyHelper.request((in, out) -> {
        out.writeUTF("/board/list");
        out.flush();

        String response = in.readUTF();
        if (response.equals("FAIL"))
          throw new Exception(in.readUTF());

        return in.readObject();
    });

  }

  @Override
  public Board findByNo(int no) throws Exception {

    return (Board) daoProxyHelper.request((in, out) -> {

        out.writeUTF("/board/detail");
        out.writeInt(no);
        out.flush();

        String response = in.readUTF();
        if (response.equals("FAIL"))
          throw new Exception(in.readUTF());

        return in.readObject();
    });

  }

  @Override
  public int update(Board board) throws Exception {

    return (int) daoProxyHelper.request((in, out) -> {
        out.writeUTF("/board/update");
        out.writeObject(board);
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
        out.writeUTF("/board/delete");
        out.writeInt(no);
        out.flush();

        String response = in.readUTF();
        if (response.equals("FAIL"))
          throw new Exception(in.readUTF());

        return 1;
    });
  }



}
