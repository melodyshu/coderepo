package com.cestc.bigdata.service.common.exception;

/**
 * 统一异常编码
 *
 * @author 
 */
public enum ResultCode {
  /**
   * 未定义异常
   */
  UNDEFINED("000000", "未定义异常");


  private String code;

  private String type;

  public String getCode() {
    return code;
  }

  public ResultCode setCode(String code) {
    this.code = code;
    return this;
  }

  public String getType() {
    return type;
  }

  public ResultCode setType(String type) {
    this.type = type;
    return this;
  }

  ResultCode(String code, String type) {
    this.code = code;
    this.type = type;
  }
}
