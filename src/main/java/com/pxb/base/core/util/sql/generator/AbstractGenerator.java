/**
 * 
 */
package com.pxb.base.core.util.sql.generator;

import java.util.List;
import java.util.Map;

import com.pxb.base.core.util.ClassUtils;
import com.pxb.base.core.util.StringUtil;
import com.pxb.base.core.util.sql.Entity;
import com.pxb.base.core.util.sql.annotation.Colum;
import com.pxb.base.core.util.sql.annotation.Table;
import com.pxb.base.core.util.sql.exception.SqlGenerateException;


/**  
* @ClassName: AbstractGenerator  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author panxiaobo  
* @date 2017年12月8日 下午2:42:58  
*    
*/
public abstract class AbstractGenerator {
	
	public final static String INSERT_SQL = "INSERT INTO tableName (column) VALUES (values);";
	public final static String UPDATE_SQL = "UPDATE tableName SET column WHERE pk=#pk#;";
	public final static String TABLE_NAME = "tableName";
	public final static String TABLE_COLUMN = "column";
	public final static String TABLE_VALUES = "values";
	public final static String TABLE_PK = "pk";
	public final static String CREATE_SQL = "CREATE TABLE tableName (column \n) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
	
	
	
	/**
	 * 获取建表语句模板
	 * @return
	 */
	abstract String getCreateTableSqlTemplate();
	
	/**
	 * 获取根据pk更新sql语句模板
	 * @return
	 */
	abstract String getUpdateSqlByPkTemplate();
	
	/**
	 * 获取添加sql模板
	 * @return
	 */
	abstract String getInsertSqlTemplate();
	
	/**
	 * 获取根据pk删除sql模板
	 * @return
	 */
	abstract String getDeleteSqlByPkTemplate();
	
	/**
	 * 获取根据pk查询sql模板
	 * @return
	 */
	abstract String getSelectSqlByPkTemplate();

	/**
	 * 生成插入sql
	 * 
	 * @param entity
	 *            实体类
	 * @param pkName
	 *            主键字段名
	 * @param isAutoInc
	 *            主键是否自增
	 * @return
	 */
	public  String createInsertSql(Object entity, String pkName, boolean isAutoInc) {
		StringBuilder column = new StringBuilder();
		StringBuilder values = new StringBuilder();
		List<Map<String, Object>> list = ClassUtils.getFiledsInfoNoStatic(entity.getClass(), Colum.class);
		String sql = new String(INSERT_SQL);
		sql = sql.replace(TABLE_NAME, entity.getClass().getSimpleName());
		int j = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).get("name").equals(pkName)) {
				continue;
			}
			if (j != 0) {
				column.append(",");
				values.append(",");
			}
			j++;
			column.append(list.get(i).get("name"));
			values.append("#").append(list.get(i).get("name")).append("#");

		}
		sql = sql.replace(TABLE_COLUMN, column.toString());
		sql = sql.replace(TABLE_VALUES, values.toString());

		return sql;
	}

	/**
	 * 生成更新sql
	 * 
	 * @param entity
	 *            实体类
	 * @param pkName
	 *            主键字段名
	 * @param isAutoInc
	 *            主键是否自增
	 * @return
	 */
	public  String createUpdateSql(Object entity, String pkName) {
		StringBuilder column = new StringBuilder();
		StringBuilder values = new StringBuilder();
		String sql = "";
		List<Map<String, Object>> list = ClassUtils.getFiledsInfoNoStatic(entity.getClass(), Colum.class);
		sql = new String(UPDATE_SQL);
		sql = sql.replace(TABLE_NAME, entity.getClass().getSimpleName());
		sql = sql.replace(TABLE_PK, pkName);
		int j = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).get("name").equals(pkName)) {
				continue;
			}
			if (j != 0) {
				column.append(",");
			}
			j++;
			column.append(list.get(i).get("name"));
			column.append("=#").append(list.get(i).get("name")).append("#");

		}
		sql = sql.replace(TABLE_COLUMN, column.toString());
		sql = sql.replace(TABLE_VALUES, values.toString());

		return sql;
	}


	/**
	 * 生成建表语句,格式参见TestUser类
	 * 
	 * @param entity
	 *            实体类
	 * @param entityComment
	 *            实体类注解
	 */
	public  <T extends Entity> String createTableSql(T entity) {
		StringBuilder column = new StringBuilder();
		String sql = "";
		List<Map<String, Object>> list = ClassUtils.getFiledsInfoNoStatic(entity.getClass(), Colum.class);
		sql = new String(getCreateTableSqlTemplate());
		
		String tableName=entity.getClass().getSimpleName();
		if(entity.getClass().isAnnotationPresent(Table.class)) {
			Table table=entity.getClass().getAnnotation(Table.class);
			 if(StringUtil.isNotEmpty(table.value())){
				 tableName=table.value();
			 }
		}
		sql = sql.replace(TABLE_NAME, tableName);
		int j = 0;
		for (int i = 0; i < list.size(); i++) {
			if (j != 0) {
				column.append(",");
			}
			j++;
			column.append("\n");
			Colum columAnnotation = (Colum) list.get(i).get("columAnnotation");
			column.append(list.get(i).get("name"));
			if (columAnnotation == null) {
				throw new SqlGenerateException(entity.getClass().getSimpleName() + "属性" + list.get(i).get("name") + "注解没有配置");
			}
			column.append(" ").append(columAnnotation.jdbcType());
			if (columAnnotation.id()) {
				column.append(" ").append("PRIMARY KEY");
			}
			if (!columAnnotation.nullable()) {
				column.append(" ").append("NOT NULL");
			}
			column.append(" '");
		}
		sql = sql.replace(TABLE_COLUMN, column.toString());
		return sql;
	}


	public static void main(String[] args) {
		// System.out.println(SqlUtil.createInsertSql(new UserMiaoLongRecord(), "",
		// false));
		// System.out.println(SqlUtil.createUpdateSql(new UserMiaoLongRecord(),
		// "userId"));
		// System.out.println(SqlUtil.createTableSql(new EquipLevelUpItemRelation(),
		// "装备强化关联表"));
		// System.out.println(SqlUtil.createTableSql(new HeroSoulRefiningTemplate(),
		// "将魂炼制模板"));
		// System.out.println(SqlUtil.createTableSql(new
		// HeroSoulRefiningRandomTemplate(), "将魂炼制随机模板"));
		// System.out.println(SqlUtil.createTableSql(new XiuFengConsumeTemplate(),
		// "绣凤消耗模板"));
		// System.out.println(SqlUtil.createTableSql(new XiuFengTemplate(), "绣凤模板"));
		// System.out.println(SqlUtil.createTableSql(new NPCTroop(), "NPC军团"));
		// SqlUtil.createShardingSql("E:\\table.sql", "HeroSoulRefiningTemplate", 100);
		// System.out.println(SqlUtil.getTableDesc(new HeroEffect()));
	}

}
