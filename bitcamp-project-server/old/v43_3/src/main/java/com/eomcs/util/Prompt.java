package com.eomcs.util;

import java.io.PrintStream;
import java.sql.Date;
import java.util.Scanner;

public class Prompt {

  public static String getString(Scanner in, PrintStream out, String title) {
    out.println(title);
    out.println("!{}!");
    out.flush();
    return in.nextLine();
  }
  
  public static String getString(Scanner in, PrintStream out, String title, String defaultValue) {
    out.println(title);
    out.println("!{}!");
    out.flush();
    String value = in.nextLine();
    if(value.length() == 0) return defaultValue;
    else
      return value;
  }
  
  
  public static int getInt(Scanner in, PrintStream out, String title) {
    try {
      return Integer.parseInt(getString(in, out, title));
    } catch(Exception e) {
      return 0;
    }
  }
  
  public static int getInt(Scanner in, PrintStream out, String title, String defaultValue) {
    try {
      return Integer.parseInt(getString(in, out, title, defaultValue));
    } catch(Exception e) {
      return 0;
    }
  }
  
  public static Date getDate(Scanner in, PrintStream out, String title) {
    try {
      return Date.valueOf(getString(in, out, title));
    } catch(Exception e) {
      return null;
    }
  }

  public static Date getDate(Scanner in, PrintStream out, String title, String defaultValue) {
    try {
      return Date.valueOf(getString(in, out, title, defaultValue));
    } catch(Exception e) {
      return null;
    }
  }
  
  
  
}
