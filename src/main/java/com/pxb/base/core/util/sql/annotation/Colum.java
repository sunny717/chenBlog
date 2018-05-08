/**
 * @ClassName:     SqlColumAnnotation.java
 * @Description:   数据库自动生成注解
 * 
 * @author         panxiaobo
 * @version        V1.0.0  
 * @Date           2016年4月11日 上午10:29:55 
 */
package com.pxb.base.core.util.sql.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.JDBCType;


/**
 * 数据库字段
 * 
 * @author panxiaobo
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Colum {
	
	/**
	 * 字段名
	 * @return
	 */
	String name() default "";
	/**
	 * 数据类型
	 * 
	 * @return
	 */
	JDBCType jdbcType() default JDBCType.NULL;

	/**
	 * 是否主键
	 * 
	 * @return
	 */
	public boolean id() default false;

	/**
	 * 是否可为null
	 * 
	 * @return
	 */
	public boolean nullable() default true;
	
    /**
     * 插入时否插入此字段
     */
    boolean insertable() default true;

    /**
     * 更新时是否更新此字段 
     * 
     */
    boolean updatable() default true;
	
	/**
	 * 字段长度
	 * @return
	 */
	public int length() default 255;
	
	/**
	 * 字段长度
	 * @return
	 */
	public int auto() default 255;

}
