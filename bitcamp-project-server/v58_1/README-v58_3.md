# 58_3 - Spring WebMVC 적용하기3 : DispatcherServlet 여러개 설정하기

## 학습목표

- 

## 실습 소스 및 결과

- src/main/java/com/eomcs/lms/ContextLoaderListenener.java 삭제
- src/main/java/com/eomcs/lms/servlet/DispatcherServlet.java 삭제
- src/main/java/com/eomcs/lms/filter/CharacterEncodingFilter.java 삭제
- src/main/java/com/eomcs/lms/util/RequestHandler.java 삭제
- src/main/java/com/eomcs/lms/util/RequestMapping.java 삭제
- src/main/java/com/eomcs/lms/util/RequestMappingHandlerMapping.java 삭제
- src/main/webapp/WEB-INF/web.xml 변경

## 실습  

### 훈련1: WebApplicationInitializer를 사용하여, DispatcherServlet을 설정한다.

- com.eomcs.lms.web.AppWebApplicationInitializer 추가

