package com.cestc.bigdata.service.common.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * 业务异常定义,可扩展为多种异常类型<p/>
 * <p>
 * 该类只负责异常定义 ，不负责具体异常处理
 *
 * @author 
 */
@Slf4j
public class BizException extends RuntimeException {

  /**
   * 业务异常编码
   */
  private ResultCode resultCode;

  public BizException() {

  }

  public BizException(String message) {

    super(message);
  }

  public BizException(String message, Throwable e) {
    super(message, e);
  }

  public BizException(String message, ResultCode resultCode, Throwable e) {
    super(message, e);
    this.resultCode = resultCode;

  }
}
