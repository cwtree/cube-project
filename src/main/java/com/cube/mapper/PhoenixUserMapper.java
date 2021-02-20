package com.cube.mapper;

import com.cube.pojo.doo.PhoenixUser;

import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * <p>
 * User Mapper
 * </p>
 *
 * @description: User Mapper
 * @author phoenix
 * @date 2020年2月8日
 */
public interface PhoenixUserMapper extends Mapper<PhoenixUser>, IdsMapper<PhoenixUser>, MySqlMapper<PhoenixUser> {

	/**
	 * 根据用户名统计（假设它是一个很复杂的SQL）
	 * 
	 * @param name 用户名
	 * @return 统计结果
	 */
	int countByName(String name);

}
