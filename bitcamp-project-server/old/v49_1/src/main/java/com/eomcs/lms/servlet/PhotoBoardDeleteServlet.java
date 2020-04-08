package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import com.eomcs.lms.service.PhotoBoardService;
import com.eomcs.util.Prompt;
import com.eomcs.util.RequestMapping;

@Component
public class PhotoBoardDeleteServlet {
  PhotoBoardService photoBoardService;

  public PhotoBoardDeleteServlet(PhotoBoardService photoBoardService) {
    this.photoBoardService = photoBoardService;
  }

  @RequestMapping("/photoboard/delete")
  public void service(Scanner in, PrintStream out) throws Exception {

    int photoFileNo = Prompt.getInt(in, out, "사진 번호?");
    photoBoardService.delete(photoFileNo);
    out.println("사진 게시글을 삭제했습니다.");

  }
}
