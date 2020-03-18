package com.eomcs.sql;

public class TransactionTemplate {

  PlatformTransactionManager txManager;

  public TransactionTemplate(PlatformTransactionManager txManager) {
    this.txManager = txManager;
  }

  // 틀 만들기!
  public Object execute(TransactionCallback action) throws Exception {
    txManager.beginTransaction();

    try {
      // 트랜젝션으로 묶인 작업들
      Object result = null;

      
      // POINT) 트랜젝션으로 묶인 작업들!
      result = action.doinTransaction();
      
      
      // 예외가 발생x 정상실행시 수행한 작업을 적용한다.
      txManager.commit();
      return result;
    } catch(Exception e) {
      // 예외가 발생한 부분
      txManager.rollback();
      
       
      // 발생된 예외는 이 메서드를 호출한 쪽에 그대로 전달한다.
      throw e;
    }
    
  }


}
