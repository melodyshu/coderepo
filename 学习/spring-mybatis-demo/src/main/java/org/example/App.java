package org.example;

import java.util.List;
import org.example.mapper.CategoryMapper;
import org.example.pojo.Category;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
        CategoryMapper bean = ctx.getBean(CategoryMapper.class);
        List<Category> list = bean.list();
        System.out.println(list);

    }
}
