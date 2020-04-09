# 55_4 - 포워딩과 인클루딩 활용

## 학습목표

- 리프래시를 구현할 수 있다.
- 리다이렉트를 구현할 수 있다.
- 리프래시와 리다이렉트의 차이점을 이해한다.

## 실습 소스 및 결과

- src/main/java/com/eomcs/lms/servlet/ErrorServlet.java 추가
- src/main/java/com/eomcs/lms/servlet/XxxServlet 변경


## 실습  

### 훈련1: 오류 내용가 발생하면, ErrorServlet으로 포워딩한다.

- com.eomcs.lms.servlet.XxxServlet 변경
  - 정상적으로 실행했을 경우 목록 화면으로 포워딩한다.
  - 오류가 발생했을 경우 ErrorServlet 으로 포워딩한다.
- com.eomcs.lms.servlet.ErrorServlet 변경
  - ServletRequest에서 
