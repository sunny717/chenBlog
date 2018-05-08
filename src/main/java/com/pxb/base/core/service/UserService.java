/**
 * 
 */
package com.pxb.base.core.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pxb.base.core.domain.User;

/**  
* @ClassName: UserService  
* @Description: 用户服务层实现类 
* @author panxiaobo  
* @date 2017年12月5日 下午6:19:45  
*    
*/
@Service
public class UserService extends BaseService<User> {
	
		
	/**
	 * 校验用户有效性
	 * @param userName
	 * @param password
	 * @return
	 */
	public boolean checkUserCanLogin(String userName, String password) {
		User query=new User();
		query.setUsername(userName);
		User user=this.queryOne(query);
		boolean canLogin=false;
		if(user!=null&&user.getPassword()!=null
				&password.equals(user.getPassword())) {
			canLogin=true;
		}
		return canLogin;
	}

	public List<User> getAllUser() {
		return this.queryAll();
	}

}
