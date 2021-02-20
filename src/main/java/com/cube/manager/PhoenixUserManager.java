package com.cube.manager;

import com.cube.pojo.doo.PhoenixUser;

/**
 * 
 * 
 * @author phoenix
 * @date 2021年2月18日
 */
public interface PhoenixUserManager {

	/**
	 * 根据ID查找用户
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 */
	PhoenixUser getPuById(long id);

	/**
	 * 根据用户名查找用户
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param username
	 * @return
	 */
	PhoenixUser getPuByName(String username);

	/**
	 * 根据手机号查找用户
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param phone
	 * @return
	 */
	PhoenixUser getPuByPhone(String phone);

	/**
	 * 保存用户信息
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param pu
	 * @return
	 */
	int savePu(PhoenixUser pu);

	/**
	 * 根据对象删除用户
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param pu
	 * @return
	 */
	int deleteUser(PhoenixUser pu);

}
