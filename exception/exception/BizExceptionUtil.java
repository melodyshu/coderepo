package com.cestc.bigdata.service.common.exception;

import com.google.common.base.Preconditions;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.message.ParameterizedMessage;

/**
 * @author 
 */
@Slf4j
public class BizExceptionUtil {

  public static RuntimeException buildRuntimeException(Throwable e) {
    log.error("异常编码:[{}] ,异常类型: [{}] ", ResultCode.UNDEFINED.getCode(), ResultCode.UNDEFINED.getType(), e);
    return new RuntimeException(e);
  }

  public static RuntimeException buildRuntimeException(String message, Throwable e) {
    log.error("异常编码:[{}] ,异常类型: [{}] , 异常信息: {} ", ResultCode.UNDEFINED.getCode(), ResultCode.UNDEFINED.getType(),
        message, e);
    return new RuntimeException(message, e);
  }

  public static BizException buildBizException(String message, Throwable e) {
    //会使用默认的resultCode
    log.error("异常编码:[{}] ,异常类型: [{}] ", ResultCode.UNDEFINED.getCode(), ResultCode.UNDEFINED.getType(), e);
    return new BizException(message, e);
  }

  public static BizException buildBizException(ResultCode resultCode, String message, Throwable e) {
    log.error("异常编码:[{}] ,异常类型: [{}] ", resultCode.getCode(), resultCode.getType(), e);
    return new BizException(message, resultCode, e);
  }

  /**
   * 遵循log4j的参数传递格式
   *
   * @param message
   * @param arguments
   * @return
   */
  public static BizException buildBizException(String message, Object... arguments) {
    return buildBizException(ResultCode.UNDEFINED, message, arguments);
  }

  /**
   * 遵循log4j的参数传递格式
   *
   * @param message
   * @param arguments
   * @return
   */
  public static BizException buildBizException(ResultCode resultCode, String message, Object... arguments) {
    Object obj = arguments[arguments.length - 1];
    Preconditions.checkArgument(obj instanceof Throwable, "最后一个参数必须是Throwable类型");
    Throwable e = (Throwable) obj;
    log.error("异常编码:[{}] ,异常类型: [{}] ", resultCode.getCode(), resultCode.getType(), arguments);
    if (arguments.length == 1) {
      return new BizException(message, e);
    } else {
      ParameterizedMessage parameterizedMessage = new ParameterizedMessage(message,
          Arrays.copyOfRange(arguments, 0, arguments.length - 1));
      return new BizException(parameterizedMessage.getFormattedMessage(), e);
    }
  }
}
