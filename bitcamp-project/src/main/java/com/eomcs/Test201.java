package com.eomcs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Test201 {
  public static void main(final String[] args) {
    final Map<String, String> map = new HashMap<>();

    map.put("1", "첫번째 값");
    map.put("2", "두번째 값");
    map.put("3", "세번째 값");
    map.put("4", "네번째 값");


    for (final String a : map.values()) {
      System.out.println(a);
    }
    // map.clear();
    System.out.println(map.get("1"));
    map.put("전화번호", "010-1234-5678");
    System.out.println(map.getOrDefault("전화번호", "없음"));


    final Iterator i = map.keySet().iterator();
    while (i.hasNext()) {
      final String key = (String) i.next();
      System.out.printf("%s : %s \n", key, map.get(key));
    }


    System.out.println("==================================");
    for (final String key : map.keySet()) {
      System.out.printf("%s : %s \n", key, map.get(key));
    }


    for (final Map.Entry entry : map.entrySet()) {
      System.out.printf("%s : %s\n", entry.getKey(), entry.getValue());
    }

    final Set<String> s = new HashSet<>();
    s.add("a");
    s.add("b");
    s.add("c");
    s.add("d");
    s.add("e");
    final Iterator<String> iter = s.iterator();
    while (iter.hasNext()) {
      System.out.println(iter.next());
    }
  }
}
