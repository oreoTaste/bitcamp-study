package com.eomcs.lms.dao.json;

import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

public class BoardJsonFileDao extends AbstractJsonFileDao<Board> implements BoardDao {

  public BoardJsonFileDao(String fileName) {
    super(fileName);
  }

  @Override
  protected <K> int indexOf(K key) {
    for(int i = 0; i < list.size(); i++) {
      if(list.get(i).getNo() == (int) key)
        return i;
    } return -1;
  }
  
  @Override
  public int insert(Board board) throws Exception {

    if (indexOf(board.getNo()) > -1) // 같은 번호의 게시물이 있다면,
      return 0;
    list.add(board); // 새 게시물을 등록한다.
    saveData();
    return 1;
  }
  

  @Override
  public List<Board> findAll() throws Exception {
    return list;
  }

  @Override
  public Board findByNo(int no) throws Exception {
    int index = indexOf(no);
    if(index == -1)
      return null;
    return list.get(index);
  }

  @Override
  public int update(Board board) throws Exception {
    int index = indexOf(board.getNo());
    if(index == -1)
      return 0;
    list.set(index, board);
    saveData();
    return 1;
  }

  @Override
  public int delete(int no) throws Exception {
    int index = indexOf(no);
    if(index == -1)
      return 0;
    list.remove(index);
    saveData();
    return 1;
  }
}
