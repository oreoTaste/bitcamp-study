# 42_2 - SQL 삽입 공격과 자바 시큐어 코딩: PreparedStatement로 전환하기

PreparedStatement를 이용하여 SQL 문과 값을 분리하여 실행하면,
SQL 삽입 공격을 막을 수 있다.


## 학습목표

- SQL 삽입 공격이 무엇인지 안다.
- SQL 삽입 공격을 막기 위한 방법을 안다.
- Statement와 PreparedStatement의 차이점을 이해한다.

## 실습 소스 및 결과 

- src/main/java/com/eomcs/lms/dao/MemberDao.java 변경
- src/main/java/com/eomcs/lms/dao/mariadb/MemberDaoImpl.java 변경
- src/main/java/com/eomcs/lms/servlet/LoginServlet.java 추가
- src/main/java/com/eomcs/lms/ServerApp.java 변경

## 실습  

### 훈련1: 사용자 로그인 기능을 만들라.

- lms_member 테이블의 암호 초기화
  - 테스트하기 위해 모든 회원의 암호를 '1111'로 초기화 한다.
  - update lms_member set pwd=password('1111') 실행
- com.eomcs.lms.dao.MemberDao 변경
  - 이메일과 암호를 가지고 사용자를 조회하는 메서드를 추가한다.
  - Member findByEmailAndPassword(String email, String password)
- com.eomcs.lms.dao.mariadb.MemberDaoImpl 변경
  - MemberDao에 추가한 메서드를 구현한다.
  - insert(), update()의 SQL 문에서 암호를 입력하거나 변경할 때 
    password() SQL 함수로 인코딩하도록 SQL 문을 변경한다.
- com.eomcs.lms.servlet.LoginServlet 추가
  - 사용자로부터 이메일과 암호를 입력받아 인증을 수행한다.
- com.eomcs.lms.ServerApp 변경
  - "/auth/login" 명령을 처리할 LoginServlet 객체를 맵에 추가한다.
  
'ClientApp' 실행 예:
```
명령> /auth/login
이메일?
user1@test.com
암호?
1111
'홍길동'님 환영합니다.

명령> /auth/login
이메일? 
user1@test.com
암호?
2222
사용자가 정보가 유효하지 않습니다.
```

### 훈련2: SQL 삽입 공격을 통해 유효하지 않은 사용자 정보로 로그인 해 보라.

'ClientApp' 실행 예:
```
명령> /auth/login
이메일?
user3@test.com
암호?
aaa') or (email='user3@test.com' and 'a'='a
'user3'님 환영합니다.

```

### 훈련3: 기존의 Statement 객체를 PreparedStatement 객체로 모두 바꿔라.

- com.eomcs.lms.dao.mariadb.XxxDaoImpl 변경
  - Statement를 PreparedStatement 로 변경한다.

### 훈련4: SQL 삽입 공격을 통해 유효하지 않은 사용자 정보로 로그인 해 보라.

'ClientApp' 실행 예:
```
명령> /auth/login
이메일?
user3@test.com
암호?
aaa') or (email='user3@test.com' and 'a'='a
사용자의 정보가 유효하지 않습니다.

```






