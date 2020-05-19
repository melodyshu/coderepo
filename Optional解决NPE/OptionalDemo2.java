package org.example;

import java.util.Optional;

/**
 * @Author: huangzhimao
 * @Date: 2020-05-19
 * @Description:
 */
public class OptionalDemo2 {

  public static void main(String[] args) {
    String str = null;
    System.out.println(getStrLen(str));
    System.out.println(getStrLen2(str));
    System.out.println(getStrLen3(str));
  }

  public static int getStrLen(String str) {
    if (null == str) {
      return 0;
    }
    return str.length();
  }

  public static int getStrLen2(String str){
    return Optional.ofNullable(str).map(String::length).orElse(0);
  }

  public static int getStrLen3(String str){
    Optional<String> op=Optional.ofNullable(str);
    String s = op.orElse("");
    Optional<Integer> op2=Optional.of(s.length());
    return op2.orElse(0);
  }
}
