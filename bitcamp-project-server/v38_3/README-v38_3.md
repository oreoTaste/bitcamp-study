# 38_3 - 리팩토링


## 학습목표

- 여러 개의 DB 변경 작업을 한 작업 단위로 묶는 트랜잭션을 다룰 수 있다.
- `commit`과 `rollback`을 활용할 수 있다.

## 실습 소스 및 결과

- src/main/java/com/eomcs/domain/PhotoBoard.java 추가
- src/main/java/com/eomcs/dao/PhotoBoardDao.java 추가
- src/main/java/com/eomcs/dao/mariadb/PhotoBoardDaoImpl.java 추가
- src/main/java/com/eomcs/servlet/PhotoBoardListServlet.java 추가
- src/main/java/com/eomcs/servlet/PhotoBoardDetailServlet.java 추가
- src/main/java/com/eomcs/servlet/PhotoBoardAddServlet.java 추가
- src/main/java/com/eomcs/servlet/PhotoBoardUpdateServlet.java 추가
- src/main/java/com/eomcs/servlet/PhotoBoardDeleteServlet.java 추가
- src/main/java/com/eomcs/lms/DataLoaderListener.java 변경
- src/main/java/com/eomcs/lms/ServerApp.java 변경

## 실습  

### 훈련1: 코드 리팩토링

- com.eomcs.util.Prompt 추가
  - Client에게 입력값을 요구하는 부분을 별도의 클래스로 분리한다.
  
본격적인 리팩토링은 v38_3에서..

- com.eomcs.lms.servlet.PhotoBoardAddServlet 변경
  - 첨부 파일 삭제를 할 때 사용할 PhotoFileDao 객체를 주입 받는다.
  - 사진 게시글을 삭제하기 전에 첨부 파일을 먼저 삭제한다.
  - 그런 후 사진 게시글을 삭제한다. 
- com.eomcs.lms.ServerApp 변경
  - `PhotoBoardDeleteServlet` 객체에 PhotoFileDao 객체를 주입한다. 
  
`ClientApp` 실행 예:
```
명령> /photoboard/delete
번호?
99
해당 사진 게시글을 찾을 수 없습니다.

명령> /photoboard/delete
번호?
7
사진 게시글을 삭제했습니다.

