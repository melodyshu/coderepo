package org.example.response;

/**
 * @Author: huangzhimao
 * @Date: 2020-04-06
 */
public class ResponseMain {
    public static void main(String[] args) {
        SuccessResponseData success = ResponseData.success();
        System.out.println(success.getCode());//200
        System.out.println(success.getMessage());//请求成功

        ResponseData error = ResponseData.error("执行失败");
        System.out.println(error.getCode());//500
        System.out.println(error.getMessage());//执行失败


    }
}
