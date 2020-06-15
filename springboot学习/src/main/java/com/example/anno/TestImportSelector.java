package com.example.anno;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author: huangzhimao
 * @Date: 2020-06-15
 * @Description:
 */
public class TestImportSelector implements ImportSelector {

  @Override
  public String[] selectImports(AnnotationMetadata importingClassMetadata) {
    return new String[]{"com.example.anno.TestBean3"};
  }
}
