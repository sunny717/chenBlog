/**
 * 
 */
package com.pxb.base.core.util;

/**  
* @ClassName: StringUtil  
* @Description: String工具类 
* @author panxiaobo  
* @date 2017年12月8日 下午4:37:37  
*    
*/
public class StringUtil {

	
    public static boolean isEmpty(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}
