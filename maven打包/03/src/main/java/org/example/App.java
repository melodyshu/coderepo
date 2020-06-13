package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception {
        System.out.println( "Hello World!" );
        String str="aaa";
        if(StringUtils.isEmpty(str)){
            System.out.println("空字符串");
        }else{
            System.out.println(str);
        }
    }
}
