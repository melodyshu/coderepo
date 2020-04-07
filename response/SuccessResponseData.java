package org.example.response;

/**
 * @Author: huangzhimao
 * @Date: 2020-04-06
 */
public class SuccessResponseData extends ResponseData {
    public SuccessResponseData() {
        super(true, DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS_MESSAGE, null);
    }

    public SuccessResponseData(Object object) {
        super(true, DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS_MESSAGE, object);
    }

    public SuccessResponseData(Integer code, String message, Object object) {
        super(true, code, message, object);
    }
}
