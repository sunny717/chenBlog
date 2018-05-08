/**
 * 
 */
package com.pxb.base.core.util.sql.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

/**
 * @ClassName: Table
 * @Description: 数据库表
 * @author panxiaobo
 * @date 2017年12月7日 下午6:12:52
 * 
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Table {


	@AliasFor("name")
	public String value() default "";
	/**
	 * 表名
	 * @return
	 */
	@AliasFor("value")
	public String name() default "";

}
