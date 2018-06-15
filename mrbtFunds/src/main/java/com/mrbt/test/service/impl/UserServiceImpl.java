package com.mrbt.test.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mrbt.test.dao.TestUserMapper;
import com.mrbt.test.model.TestUser;
import com.mrbt.test.service.UserService;

@Service("userService") 
public class UserServiceImpl implements UserService {
	
	@Resource
	private TestUserMapper userDao;

	@Override
	public TestUser getUserById(String userId) {
		return this.userDao.selectByPrimaryKey(userId);
	}

}
