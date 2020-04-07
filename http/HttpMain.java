package org.example.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: huangzhimao
 * @Date: 2020-04-07
 */
public class HttpMain {
    private static final Logger log = LoggerFactory.getLogger(HttpMain.class);
    public static void main(String[] args) {
        String url="http://127.0.0.1:8080/query?name=zhangsan&age=24";
        String result = HttpUtils.sendGet(url);
        System.out.println(result);

        /*String url2="http://127.0.0.1:8080/query2?name=sdfs";
        String result2 = HttpUtils.sendPost(url2);
        System.out.println(result2);*/
    }
}
