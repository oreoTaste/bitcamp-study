package com.eomcs.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
  // 서블릿 메서드와 명령어를 연결하기 위해
  // 명령어를 저장할 프로퍼티를 정의한다.
  String value();
  
}
