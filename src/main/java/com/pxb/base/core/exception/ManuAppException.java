/**
 * 
 */
package com.pxb.base.core.exception;

import org.springframework.core.NestedRuntimeException;

/**  
* @ClassName: ManuAppException  
* @Description: 统一异常
* @author panxiaobo  
* @date 2018年1月4日 下午4:10:06  
*    
*/
public class ManuAppException extends NestedRuntimeException
{
  public ManuAppException(String msg)
  {
    super(msg);
  }

  public ManuAppException(String msg, Throwable cause)
  {
    super(msg, cause);
  }
  public ManuAppException() {
    super("not implements: manuapp");
  }
}
