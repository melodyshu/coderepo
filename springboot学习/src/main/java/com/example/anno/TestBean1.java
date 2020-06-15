package com.example.anno;

/**
 * @Author: huangzhimao
 * @Date: 2020-06-15
 * @Description:
 */
public class TestBean1 {

  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "TestBean1{" +
        "name='" + name + '\'' +
        '}';
  }
}
