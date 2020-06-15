package com.example.anno;

import org.springframework.context.annotation.Configuration;

/**
 * @Author: huangzhimao
 * @Date: 2020-06-15
 * @Description:
 */
@Configuration
public class TestBean2 {

  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "TestBean2{" +
        "name='" + name + '\'' +
        '}';
  }
}
