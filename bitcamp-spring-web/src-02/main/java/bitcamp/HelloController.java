package bitcamp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// 프론트 컨트롤러 (Dispatcher Controller)가 수행할 페이지 컨트롤러는
// 다음과 같이 @Controller애노테이션을 붙여야한다.
@Controller
public class HelloController {

  // 클라이언트 요청이 들어왓을때 호출될 메서드(Request Handler)를 표시하려면
  // 다음과 같이 @RequestMapping(요청URL) 애노테이션을 붙인다.
  @RequestMapping({"/hello","/hello2","/hello3"})
  
  // 리턴값이 String값이 뷰 컴포넌트(JSP등)의 URL이 아닌경우, 애노테이션으로 표시한다.
  // (@ResponseBody : 리턴하는 문자열이 클라이언트에게 보낼 콘텐트임을 표시한다.)
  @ResponseBody
  public String hello() throws Exception {
    return "<html><body><h1>Hello!</h1></body></html>";
  }
}
