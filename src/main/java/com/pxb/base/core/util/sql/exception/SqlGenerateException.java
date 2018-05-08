/**
 * 
 */
package com.pxb.base.core.util.sql.exception;

/**  
* @ClassName: SqlGenerateException  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author panxiaobo  
* @date 2017年12月8日 下午6:20:53  
*    
*/
@SuppressWarnings("serial")
public class SqlGenerateException extends RuntimeException{
	
	public SqlGenerateException(String msg)
	  {
	    super(msg);
	  }

	  public SqlGenerateException(String msg, Throwable cause)
	  {
	    super(msg, cause);
	  }
	  public SqlGenerateException() {
	    super("no message");
	  }

}
