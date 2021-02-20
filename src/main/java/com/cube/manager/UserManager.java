package com.cube.manager;

import com.cube.pojo.doo.User;

/**
 * 
 * 
 * @author phoenix
 * @date 2021年2月10日
 */
public interface UserManager {

	/**
	 * 根据主键查询用户
	 * 
	 * @Description: 根据主键查询用户
	 * @param id
	 * @return
	 */
	User getUserById(long id);

	/**
	 * 保存用户，返回影响行数
	 * 
	 * @Description: 保存用户，返回影响行数
	 * @param user
	 * @return
	 */
	int saveUser(User user);

}
