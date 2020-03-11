package com.eomcs.sql;

public interface TransactionCallback {
  
  // 이 메서드에 트랙잭션으로 묶어서 다룰 작업을 기술한다.
  Object doinTransaction() throws Exception;
 
  
}
