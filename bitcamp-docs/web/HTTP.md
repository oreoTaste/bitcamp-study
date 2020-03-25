# HTTP 프로토콜

# HTTP 요청

- 프로토콜 형식
  [method] [request-uri] [http-version] [CRLF]
  ([general|request|entity-header] [CRLF])*
  [CRLF]
  [message-body]
  
- method
  - 서버에 요청하는 명령
  - GET : 서버에 자원를 요청하는 명령어 (데이터 받기)
  - POST : 서버에 자원을 보내는 명령어 (데이터 보내기)
  - HEAD : 지정된 자원의 정보만 받기 (헤더정보만 받기)
  - PUT : 지정한 자원의 변경을 요구 (post + 저장)
  - DELETE : 지정한 자원 삭제를 요구 (리소스 삭제)

- request-uri
  - 서버 자원의 경로
  - 예) /news/108939, /index.html, /board/list
  
- http-version
  - 통신 프로토콜의 버전
  - 예) HTTP/1.1
  
- header
  - 클라이언트가 서버에 보내는 부가정보
  
  > general-header
    - 예) 헤더명 : 값 CRLF
    - 요청과 응답 모두에서 사용하는 데이터에 대한 부가정보
      - 예) Connection
  > request-header
    - 요청하는 쪽에서 보낼 수 있는 부가정보
      - 예) Accept : text/*, text/html, text/html;level=1, */*
      - 예) Accept-language : ko, en-gb;q=0.8, en=0.7
      - 예) Referer : http://www.w3.org/hypertext/DataSources/Overview/html
  > entity-header
    - 요청과 응답 모두에서 사용하는 데이터에 대한 부가정보
    - 보내는 데이터에 대한 정보
      - 예) Content-Type: text/html; charset=utf-8
      - 예) Content-Length : 3495
      - 예) Last-Modified: Tue, 15 Nov 1994 12:45:26 GMT
  > response-header
    - 응답하는 쪽에서 보낼 수 있는 부가정보
    - 예) CERN/3.0 libwww/2.17
    - 예) Location : http://www.w3.org/pub/WWW/People.html
    
HTTP 요청 예 : POST /new/108939/ HTTP/1.1 \r\n

// 예시) Request Header
GET / HTTP/1.1
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36
Sec-Fetch-Dest: document



## HTTP 응답

- 프로토콜 형식
  [http-version] [status-code] [reason-phrase] [CRLF]
  ([general|request|entity-header] [CRLF])*
  [CRLF]
  [message-body]
  
- status-code
  - 1__(일반정보)
  - 2__(성공했음을 표현)
    - 200 : 요청을 정상적으로 수행.
    - 201 : 요청한 자원을 생성함.
  - 3__(요청한 자원이 다른 장소로 이동함)
    - 301 : 요청한 자원이 다른 장소로 이동함
    - 304 : 요청한 자원이 변경되지 않아서, 기존에 다운받은 것을 그대로 사용하라고 요구
  - 4__(실패했음을 표현)
    - 401 : 요청권한이 없음
    - 403 : 요청한 자원에 대한 권한이 없어 거절함.
    - 404 : 요청한 자원을 찾을 수 없음
  - 5__(서버쪽 오류발생)
    - 500 : 서버쪽에서 프로그램 수행하다가 오류발생
  

- message-body
  - 클라이언트가 Post로 요청할때, message-body 데이터를 보낸다
  - 서버에서 응답할때, message-body 데이터를 보낸다
  
// 예시) Response Header
HTTP/1.1 200 OK
Date: Wed, 25 Mar 2020 03:11:46 GMT
Server: Apache
X-Powered-By: PHP/5.6.33
Cache-Control: max-age=0
Expires: Wed, 25 Mar 2020 03:11:46 GMT
Vary: Accept-Encoding,User-Agent
Content-Encoding: gzip
Content-Length: 16291
Content-Type: text/html; charset=UTF-8

























