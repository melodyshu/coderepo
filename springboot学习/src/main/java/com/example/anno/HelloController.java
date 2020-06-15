package com.example.anno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: huangzhimao
 * @Date: 2020-06-15
 * @Description:
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

  @Autowired
  private TestBean1 testBean1;

  @Autowired
  private TestBean2 testBean2;

  @Autowired
  private TestBean3 testBean3;

  @Autowired
  private TestBean4 testBean4;

  @GetMapping("/list")
  public String hello() {
    testBean1.setName("张三");
    System.out.println(testBean1);
    testBean2.setName("李四");
    System.out.println(testBean2);
    testBean3.setName("tom");
    System.out.println(testBean3);
    testBean4.setName("jim");
    System.out.println(testBean4);
    return "ok";
  }
}
