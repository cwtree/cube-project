package com.cube.service;

import com.cube.pojo.doo.PhoenixUser;

/**
 * 
 * 
 * @author phoenix
 * @date 2021年2月10日
 */
public interface UserService {

	/**
	 * 根据主键查询用户
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 */
	PhoenixUser getUserById(long id);

	/**
	 * 根据用户名查询用户
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param name
	 * @return
	 */
	PhoenixUser getUserByName(String name);

	/**
	 * 根据手机号查询用户
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param phone
	 * @return
	 */
	PhoenixUser getUserByPhone(String phone);

	/**
	 * 新增用户
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param user
	 */
	void saveUser(PhoenixUser user);

	/**
	 * 跟id删除用户
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param user
	 * @return
	 */
	int deleteUser(PhoenixUser user);

}
