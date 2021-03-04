package com.cube.service;

import com.cube.pojo.doo.PhoenixUser;

/**
 * service层的方法理论是完成前台用户的一次请求 包含了业务逻辑，这里仅仅是举例，方法较简单，基本和manager层重复
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

	/**
	 * 更新用户头像 这是一个非常消耗资源的操作，涉及图片删除、存储，用户头像字段数据更新
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param pic
	 * @return
	 */
	String updateHeadPic(String pic);

}
