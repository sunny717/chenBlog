/**
 * 
 */
package com.pxb.base.core.util.sql;

import java.sql.JDBCType;

import com.pxb.base.core.util.sql.annotation.Colum;

/**  
* @ClassName: EntiyObj  
* @Description: 主键自增的实体父类 
* @author panxiaobo  
* @date 2017年12月8日 下午3:04:26  
*    
*/
public abstract class EntiyObj extends Entity{
	
	@Colum(jdbcType = JDBCType.BIGINT, id = true,insertable=false,updatable=false)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	

}
