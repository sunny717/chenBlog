/**
 * @ClassName:     SqlUtil.java
 * @Description:   sql自动成功工具
 * 
 * @author         panxiaobo
 * @version        V1.0.0  
 * @Date           2016年4月8日 下午6:44:56 
 */
package com.pxb.base.core.util.sql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import com.pxb.base.core.util.ClassUtils;
import com.pxb.base.core.util.sql.annotation.Colum;

/**
 * sql语句自动生成器
 * 
 * @author panxiaobo
 * 
 */
public class SqlUtil {



	/**
	 * 创建分表sql
	 * 
	 * @param filePath
	 *            文件路径
	 * @param tableName
	 *            表名
	 * @param shardNum
	 *            分表数
	 */
	public static void createShardingSql(String filePath, String tableName, int shardNum) {
		String creteSql = SqlUtil.readCreateSqlFile(filePath);
		for (int i = 0; i < shardNum; i++) {
			String str = creteSql.replace(tableName, tableName + "_" + i);
			System.out.println(str);

		}
	}

	/**
	 * 获取创建sql语句
	 * 
	 * @param filePath
	 * @return
	 */
	private static String readCreateSqlFile(String filePath) {
		StringBuilder createSql = new StringBuilder();
		try {
			String encoding = "GBK";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					createSql.append(lineTxt).append("\n");
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
		return createSql.toString();

	}
	
	
	/**
	 * 获取表结构
	 * 
	 * @param entity
	 * @param entityComment
	 * @return
	 */
	public static String getTableDesc(Object entity) {
		StringBuilder column = new StringBuilder();
		String sql = "";
		List<Map<String, Object>> list = ClassUtils.getFiledsInfoNoStatic(entity.getClass(), Colum.class);
		for (int i = 0; i < list.size(); i++) {
			Colum columAnnotation = (Colum) list.get(i).get("columAnnotation");
			column.append(list.get(i).get("name"));
			if (columAnnotation == null) {
				System.out.println(entity.getClass().getSimpleName() + "属性" + list.get(i).get("name") + "注解没有配置");
			}
			column.append(" ").append(columAnnotation.jdbcType());
			column.append("\n");
		}
		return column.toString();

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
