/**
 * 
 */
package com.pxb.base.core.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.pxb.base.core.Constants;
import com.pxb.base.core.domain.User;
import com.pxb.base.core.service.UserService;

/**  
* @ClassName: UserController  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author panxiaobo  
* @date 2017年11月29日 下午5:13:59  
*    
*/
@Controller
@RequestMapping("/user")
public class UserController extends CoreController{
	
	@Autowired
	
	private UserService userService;
	
	
    /**  
     * 用户查询
     * @return 
     */  
    @RequestMapping("/index")  
    public String getUsers(ModelMap map,HttpServletRequest req){  
    	Integer page = req.getParameter("page")==null?0:(Integer.parseInt(req.getParameter("page"))-1);
    	PageInfo<User> pages = userService.queryPageListByWhere(page, Constants.PAGE_ROWS, null);

        map.put("totals",pages.getPages());
        map.put("page",page+1);
        map.put("userlist",pages.getList());
        map.put("menu_code","mainuser");
        return "user/index";
    }
    
    
    @RequestMapping(value="/edit/{id}")
    public String get(ModelMap model,@PathVariable Long id){
        User user = userService.queryById(id);

//        List<Role> roles = roleRepository.findAll();
//
//        List<Long> rids = new ArrayList<Long>();
//        for(Role role : user.getRoles()){
//            rids.add(role.getId());
//        }

        model.addAttribute("user",user);
//        model.addAttribute("roles", roles);
//        model.addAttribute("rids", rids);

        return "user/edit";
    }
    
    
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public String update(HttpServletRequest req){
    	User userD=userService.queryById(Long.parseLong(req.getParameter("userId")));
    	userD.setEmail(this.getString(req, "email"));
    	userD.setPhone(this.getString(req, "phone"));
    	userD.setUsername(this.getString(req, "username"));
    	userService.update(userD);
        return "success";
    }

}
