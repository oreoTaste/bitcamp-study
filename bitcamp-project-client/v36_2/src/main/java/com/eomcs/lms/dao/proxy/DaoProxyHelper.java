package com.eomcs.lms.dao.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

// 역할 : DaoProxy가 서버와 통신할때, 서버와 연결 + 입출력 준비하는 역할
public class DaoProxyHelper {
  String host;
  int port;
  
  public DaoProxyHelper(String host, int port) {
    this.host = host;
    this.port = port;
  }
  
  public Object request(Worker worker) throws Exception {
    
    try(Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())){
      
      return worker.execute(in , out);
      
    }
  }
}
