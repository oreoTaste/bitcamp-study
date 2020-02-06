package com.eomcs.lms;

import java.util.Map;
import com.eomcs.lms.context.ApplicationContextListener;

public class GreetingListener implements ApplicationContextListener {

  @Override
  public void contextInitialized(Map<String, Object> context) {
    System.out.println("[수업관리시스템]에 오신 것을 환영합니다!");
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    System.out.println("[수업관리시스템] 종료!");
  }

}
