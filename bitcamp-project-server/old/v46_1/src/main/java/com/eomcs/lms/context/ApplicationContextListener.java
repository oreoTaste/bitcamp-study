package com.eomcs.lms.context;

import java.util.Map;

// 애플리케이션의 상태 변경에 대해 반응하는 Observer 규칙을 정의한다.
// 보통 Observer를 Listener라고 부름.

public interface ApplicationContextListener {
  // 애플리케이션 시작할때 호출
  void contextInitialized(Map<String, Object> context);
  
  // 애플리케이션 종료될때 호출
  void contextDestroyed(Map<String, Object> context);
}
