package com.example.anno;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author: huangzhimao
 * @Date: 2020-06-15
 * @Description:
 */
public class TestImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

  @Override
  public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
    RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(TestBean4.class);
    registry.registerBeanDefinition("testBean4", rootBeanDefinition);
  }
}
