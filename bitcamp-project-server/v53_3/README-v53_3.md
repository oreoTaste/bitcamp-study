# 53_3 - Apache HttpComponenet 라이브러리를 사용하여...

## 학습목표

- HTML 태그를 사용할 수 있다.

## 실습 소스 및 결과

- src/main/java/com/eomcs/lms/servlet/BoardXxxServlet.java 변경
- src/main/java/com/eomcs/lms/servlet/MemberXxxServlet.java 변경
- src/main/java/com/eomcs/lms/servlet/LessonXxxServlet.java 변경
- src/main/java/com/eomcs/lms/servlet/PhotoBoardXxxServlet.java 변경
- src/main/java/com/eomcs/lms/ServerApp.java 변경

## 실습  

### 훈련1: Apache HttpComponenet 라이브러리를 프로젝트에 적용한다.

- search.maven.org에서 라이브러리 정보 알아낸다.
  - 'httpclient5'로 검색
- build.gradle 변경 & gradle eclipse

### 훈련2: HTTP요청을 받을 때, HTTP Components 라이브러리에 있는 클래스를 사용한다.

- com.eomcs.lms.ServerApp 변경

### 훈련3: 서블릿의 request handler의 파라미터를 변경한다.

- com.eomcs.lms.servlet.xxxServlet 변경
  - request handler의 파라미터를 PrintStream에서 PrintWriter로 변경한다.






