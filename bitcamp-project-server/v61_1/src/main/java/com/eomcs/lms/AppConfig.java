package com.eomcs.lms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;

@ComponentScan(
    value = "com.eomcs.lms",
    excludeFilters = {
        @Filter(type = FilterType.REGEX, pattern = "com.eomcs.lms.web.*"),
        @Filter(type = FilterType.REGEX, pattern = "com.eomcs.lms.admin.*")
        }
    )

public class AppConfig {
  
  static Logger logger = LogManager.getLogger(AppConfig.class);
  
  public AppConfig() {
    logger.debug("AppConfig 객체 생성!");
  }
  
  
}









