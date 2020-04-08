package com.eomcs.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// 클래스 이름ㅇ르 지정할때 붙인 애노테이션을 정의한다.
// 컴파일 한 후에도 .class 파일에 이 애노테이션 정보를 유지하게 한다.
// JVM 에서 이 애노테이션 정보를 꺼낼 수 있도록 한다.
@Retention(RetentionPolicy.RUNTIME)
public @interface Component {
  // 이 애노테이션 적용할때
  // 컴포넌트의 이름을 지정할 수 있도록 프로퍼티를 선언한다.
  String value() default ""; // 만약 값이 지정되지 않으면 빈 문자열이 설정된다.
  
}
