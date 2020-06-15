package com.example.anno;

/**
 * @Author: huangzhimao
 * @Date: 2020-06-15
 * @Description:
 */

public class TestBean4 {

  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "TestBean4{" +
        "name='" + name + '\'' +
        '}';
  }
}
