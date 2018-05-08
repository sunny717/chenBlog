/**
 * 
 */
package com.pxb.base;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**  
* @ClassName: Boot  
* @Description: 启动器 
* @author panxiaobo  
* @date 2017年11月28日 上午11:17:42  
*    
*/
@SpringBootApplication(scanBasePackages= {"com"})
@MapperScan(basePackages = {"com.pxb.base.core.mapper"})
public class Boot {
	public static void main(String[] args) {
		SpringApplication.run(Boot.class, args);
	}

}
