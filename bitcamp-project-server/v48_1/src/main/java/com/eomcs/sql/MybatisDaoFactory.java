package com.eomcs.sql;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class MybatisDaoFactory {
  InvocationHandler invocationHandler;

  public MybatisDaoFactory(SqlSessionFactory sqlSessionFactory) {
    this.invocationHandler = (proxy, method, args) -> {

      // 이 메서드는 DAO 프록시의 메서드가 호출될 때마다,
      // SqlSession을 이용하여 SQL을 실행한다.
      // 메서드명
      // namespace + id,
      // 메서드에 필요한 파라미터
      // -> 인터페이스명과 메서드명을 이용하여 실행할 SQL 아이디를 알아낸다.
      // -> 리턴타입에 따라 호출할 메서드를 결정한다.
      // -> 파라미터 유무에 따라 메서드에 넘길 항목을 결정한다.

      //      proxy.getClass().getInterfaces()[0].getName();
      // SQL ID 알아내기
      Class<?> clazz = proxy.getClass(); // 프록시 객체의 정보를 알아낸다.
      System.out.println("[proxy의 getClass] : " + clazz);
      Class<?> daoInterface = clazz.getInterfaces()[0]; // 프록시 객체가 구현한 인터페이스 정보를 알아낸다.
      System.out.println("[proxy의Class의 getInterfaces] : " + daoInterface);
      String interfaceName = daoInterface.getName();
      String sqlId = String.format("%s.%s", interfaceName, method.getName());
      System.out.println("[인터페이스+메서드 이름]" + sqlId);

      Class<?> returnType = method.getReturnType();
      System.out.println("리턴타입:"+returnType);

      try(SqlSession sqlSession = sqlSessionFactory.openSession()){
        if(returnType == List.class)
          return args == null ? sqlSession.selectList(sqlId) : sqlSession.selectList(sqlId, args[0]);
        else if(returnType == int.class || returnType == void.class)
          // update, insert, delete 모두 같다.
          return args == null ? sqlSession.update(sqlId) : sqlSession.update(sqlId, args[0]);
        else
          return args == null ? sqlSession.selectOne(sqlId) : sqlSession.selectOne(sqlId, args[0]);
      }
    };
  }

  @SuppressWarnings("unchecked")
  public <T>T createDao(Class<T> daoInterface) {

    return (T) Proxy.newProxyInstance(
        this.getClass().getClassLoader(),
        new Class[] {daoInterface},
        invocationHandler);
  }

}
