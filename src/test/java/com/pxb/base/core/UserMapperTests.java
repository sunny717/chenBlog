package com.pxb.base.core;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pxb.base.core.domain.User;
import com.pxb.base.core.mapper.UserMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTests {
	@Autowired
	private UserMapper userMapper;
	@Test
	public void insert() {
		User u=new User();
		u.setUsername("测试1");
		u.setPassword("111");
		u.setPhone("13717690363");
		u.setCreatedate(new Date());
		u.setEmail("250553199@qq.com");
		userMapper.insert(u);
		Assert.assertTrue(userMapper.selectAll().size()>0);
	}
	@Test
	public void queryByName() {
		this.insert();
		User u=new User();
		u.setUsername("admin");
		Assert.assertNotNull(userMapper.selectOne(u));
	}
	@Test
	public void deleteAll() {
		User u=new User();
		u.setUsername("admin");
		userMapper.delete(u);
		Assert.assertNull(userMapper.selectOne(u));
	}

}
