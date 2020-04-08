package com.eomcs.util;

import java.util.HashMap;

public class RequestMappingHandlerMapping {
  HashMap<String, RequestHandler> map = new HashMap<>();
  
  public void addHandler(String name, RequestHandler requestHandler) {
    map.put(name, requestHandler);
  }
  
  public RequestHandler getHandler(String name) {
    return map.get(name);
  }
  
}


