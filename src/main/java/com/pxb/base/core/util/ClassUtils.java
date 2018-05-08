/**
 * @ClassName:     ClassUtils.java
 * @Description:   TODO(用一句话描述该文件做什么) 
 * 
 * @author         panxiaobo
 * @version        V1.0.0  
 * @Date           2016年4月8日 下午6:40:13 
 */
package com.pxb.base.core.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;

/**
 * 类操作工具，包含获取字段，字段属性
 * 
 * @author panxiaobo
 * 
 */
public class ClassUtils {
	/**
	 * 根据属性名获取属性值
	 */
	public static Object getFieldValueByName(String fieldName, Object o) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = o.getClass().getMethod(getter, new Class[] {});
			Object value = method.invoke(o, new Object[] {});
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取属性名数组
	 */
	public static String[] getFiledName(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		String[] fieldNames = new String[fields.length];
		for (int i = 0; i < fields.length; i++) {
			System.out.println(fields[i].getType());
			fieldNames[i] = fields[i].getName();
		}
		return fieldNames;
	}

	/**
	 * 获取属性类型(type)，属性名(name)的map组成的list
	 */
	public static List<Map<String, Object>> getFiledsInfo(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		List<Map<String, Object>> list = CollectionUtil.newArrayList();
		Map<String, Object> infoMap = null;
		for (int i = 0; i < fields.length; i++) {
			infoMap = CollectionUtil.newHashMap();
			infoMap.put("type", fields[i].getType().toString());
			infoMap.put("name", fields[i].getName());
			list.add(infoMap);
		}
		return list;
	}

	/**
	 * 获取某个类所有方法的某个注解
	 * 
	 * @param classObj
	 * @param class
	 *            此方法去除了所有的static,final,transient修饰的属性
	 *            获取属性类型(type)，属性名(name),注解(columAnnotation)的map组成的list
	 */
	public static <T extends Annotation> List<Map<String, Object>> getFiledsInfoNoStatic(Class classObj,
			Class<T> annotationClass) {
		List<Map<String, Object>> list = CollectionUtil.newArrayList();
		if (classObj.getSuperclass() != null) {
			list.addAll(getFiledsInfoNoStatic(classObj.getSuperclass(), annotationClass));
		}
		Field[] fields = classObj.getDeclaredFields();
		Map<String, Object> infoMap = null;
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers())
					|| Modifier.isTransient(field.getModifiers()) || fields[i].getType().getSimpleName().equals("List")
					|| fields[i].getType().getSimpleName().equals("Map")) {
				continue;
			}
			infoMap = CollectionUtil.newHashMap();
			infoMap.put("type", fields[i].getType().toString());
			infoMap.put("name", fields[i].getName());
			T columAnnotation = (T) field.getAnnotation(annotationClass);
			infoMap.put("columAnnotation", columAnnotation);
			list.add(infoMap);
		}
		return list;
	}

	/**
	 * 获取对象的所有属性值，返回一个对象数组
	 */
	public static Object[] getFiledValues(Object o) {
		String[] fieldNames = ClassUtils.getFiledName(o);
		Object[] value = new Object[fieldNames.length];
		for (int i = 0; i < fieldNames.length; i++) {
			value[i] = ClassUtils.getFieldValueByName(fieldNames[i], o);
		}
		return value;
	}

	/**
	 * 将类的首字母转换成小写
	 * 
	 * @param s
	 * @return
	 */
	public static String getLowerClassName(Class s) {
		String simpleClassName = s.getSimpleName();
		char[] chars = new char[1];
		chars[0] = simpleClassName.charAt(0);
		String temp = new String(chars);
		if (chars[0] >= 'A' && chars[0] <= 'Z') {// 当为字母时，则转换为小写
			simpleClassName = simpleClassName.replaceFirst(temp, temp.toLowerCase());
		}
		return simpleClassName;
	}

}
