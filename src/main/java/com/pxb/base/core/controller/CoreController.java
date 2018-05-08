/**
 * 
 */
package com.pxb.base.core.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import com.pxb.base.core.exception.ManuAppException;

/**  
* @ClassName: CoreController  
* @Description: 控制器父类  
* @author panxiaobo  
* @date 2018年1月4日 下午3:53:25  
*    
*/
public class CoreController {
	public Object get(HttpServletRequest req,String paramName){
		Object obj = req.getParameter(paramName);
		return obj;
	}
	
	/**
	 * 根据参数名获取对应的值，若不存在此参数则抛出异常
	 * @param key
	 * @return
	 */
	public Object getNotNull(HttpServletRequest req,String paramName){
		Object obj = req.getParameter(paramName);
		if(obj == null){
			throw new ManuAppException("parameter:" + paramName + " does not exist!!");
		}
		return obj;
	}
	
	/**
	 * 根据参数名及参数类型获取对应的值
	 * 若不存在此参数则抛出异常
	 * @param paramName
	 * @param cla
	 * @return
	 */
	public <T> T get(HttpServletRequest req,String paramName, Class<T> cla){
		return cla.cast(this.getNotNull(req,paramName));
	}
	
	/**
	 * 获取一个整形参数值，若不存在此参数则抛出异常
	 * @param paramName
	 * @return
	 */
	public int getInt(HttpServletRequest req,String paramName){
		return Integer.parseInt(this.getNotNull(req,paramName).toString());
	}
	
	/**
	 * 获取一个长整形参数值，若不存在此参数则抛出异常
	 * @param paramName
	 * @return
	 */
	public long getLong(HttpServletRequest req,String paramName){
		return Long.parseLong(this.getNotNull(req,paramName).toString());
	}
	
	/**
	 * 获取一个字符串参数值，若不存在此参数则抛出异常
	 * @param key
	 * @return
	 */
	public String getString(HttpServletRequest req,String key){
		return this.getNotNull(req,key).toString();
	}

}
