package org.example;

import org.apache.commons.lang3.StringUtils;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        String str="aaa";
        if(StringUtils.isEmpty(str)){
            System.out.println("空字符串");
        }else{
            System.out.println(str);
        }
    }
}
