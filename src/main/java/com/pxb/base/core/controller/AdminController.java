/**
 * 
 */
package com.pxb.base.core.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**  
* @ClassName: AdminController  
* @Description: 账号管理控制器
* @author panxiaobo  
* @date 2017年12月6日 上午9:53:31  
*    
*/
@Controller
@RequestMapping("/admin")
public class AdminController {
	@RequestMapping("/index")
    public String index(ModelMap map){

        map.put("ccount",0);//文章数
        map.put("lcount",0);//友链数
        map.put("acount",0);//附件数

        //最新的随机文章

        map.put("contents","");
        map.put("menu_code","mainindex");

        return "admin/index";
    }

}
