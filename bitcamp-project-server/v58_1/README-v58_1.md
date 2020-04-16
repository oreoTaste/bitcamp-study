# 58_1 - Spring WebMVC 적용하기

## 학습목표

- Spring WebMVC 프레임워크를 프로젝트에 적용할 수 있다.
- Spring WebMVC 프레임워크의 규칙에 따라 웹 애플리케이션을 작성할 수 있다.

## 실습 소스 및 결과

- src/main/java/com/eomcs/lms/ContextLoaderListenener.java 삭제
- src/main/java/com/eomcs/lms/servlet/DispatcherServlet.java 삭제
- src/main/java/com/eomcs/lms/filter/CharacterEncodingFilter.java 삭제
- src/main/java/com/eomcs/lms/util/RequestHandler.java 삭제
- src/main/java/com/eomcs/lms/util/RequestMapping.java 삭제
- src/main/java/com/eomcs/lms/util/RequestMappingHandlerMapping.java 삭제
- src/main/webapp/WEB-INF/web.xml 변경

## 실습  

### 훈련1: Spring WebMVC 프레임워크를 프로젝트에 적용한다.

- 라이브러리 가져오기

### 훈련2: 페이지 컨트롤러를 Spring WebMVC 프레임워크 사용법에 따라 변경한다.

- com.eomcs.lms.web.XXXController.java 변경
  - @Component 대신 @Controller로 변경한다.
  - @RequestMapping를 Spring WebMVC 라이브러리로 바꾼다.
  
### 훈련3: Spring WebMVC 에서 제공하는 프론트 컨트롤러 서블릿을 설정한다.

- src/main/webapp/WEB-INF/web.xml 변경
  - DispatcherServlet 변경
  - CharacterEncodingFilter 클래스를 등록한다.
  