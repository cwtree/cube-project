package com.cube.manager.impl;

import org.springframework.stereotype.Component;

import com.cube.manager.UserManager;
import com.cube.pojo.doo.User;

/**
 * 
 * 
 * @author phoenix
 * @date 2021年2月10日
 */
@Component
public class UserManagerImpl implements UserManager {

	@Override
	public User getUserById(long id) {
		// TODO 根据ID查询用户，返回对象
		return null;
	}

	@Override
	public int saveUser(User user) {
		// TODO 将用户对象持久化存储，返回本次操作影响的记录行数
		return 0;
	}

}
