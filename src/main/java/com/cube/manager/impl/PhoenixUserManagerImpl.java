package com.cube.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cube.cache.CacheDel;
import com.cube.cache.CacheGet;
import com.cube.cache.CacheKey;
import com.cube.manager.PhoenixUserManager;
import com.cube.mapper.PhoenixUserMapper;
import com.cube.pojo.doo.PhoenixUser;

import cn.hutool.core.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 
 * manager层针对数据操作层抽象 建议按照DO领域一一对应，一个DO对应一个manager 方法内部只有数据操作和日志打印，不再融入其它逻辑
 * 日后的统一缓存操作在该层通过AOP统一处理 所以要求该层方法返回类型有且只有int、do领域对象、List
 * 
 * @author phoenix
 * @date 2021年2月18日
 */
@Component
@Slf4j
public class PhoenixUserManagerImpl implements PhoenixUserManager {

	@Resource
	private PhoenixUserMapper phoenixUserMapper;

	@CacheGet(get = @CacheKey(key = "ID", value = "#id"))
	@Override
	public PhoenixUser getPuById(long id) {
		// TODO Auto-generated method stub
		PhoenixUser pu = phoenixUserMapper.selectByPrimaryKey(id);
		if (log.isInfoEnabled()) {
			log.info("根据ID {} 查找用户信息 {}", id, pu);
		}
		return pu;
	}

	@CacheGet(get = @CacheKey(key = "NAME", value = "#username"))
	@Override
	public PhoenixUser getPuByName(String username) {
		// TODO Auto-generated method stub
		Example example = new Example(PhoenixUser.class);
		Criteria c = example.createCriteria();
		c.andEqualTo("name", username);
		List<PhoenixUser> list = phoenixUserMapper.selectByExample(example);
		if (log.isInfoEnabled()) {
			log.info("根据name {} 查找用户信息 {}", username, list);
		}
		if (CollectionUtil.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	@CacheGet(get = @CacheKey(key = "PHONE", value = "#phone"))
	@Override
	public PhoenixUser getPuByPhone(String phone) {
		// TODO Auto-generated method stub
		Example example = new Example(PhoenixUser.class);
		Criteria c = example.createCriteria();
		c.andEqualTo("phoneNumber", phone);
		List<PhoenixUser> list = phoenixUserMapper.selectByExample(example);
		if (log.isInfoEnabled()) {
			log.info("根据phone {} 查找用户信息 {}", phone, list);
		}
		if (CollectionUtil.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 
	 * @see com.cube.manager.PhoenixUserManager#savePu(com.cube.pojo.doo.PhoenixUser)
	 */
	@Override
	public int savePu(PhoenixUser pu) {
		// TODO Auto-generated method stub
		int res = phoenixUserMapper.insert(pu);
		if (res > 0) {
			if (log.isInfoEnabled()) {
				log.info("保存用户 {} 成功", pu);
			}
		}
		return res;
	}

	@CacheDel(del = { @CacheKey(key = "ID", value = "#pu.id"), @CacheKey(key = "NAME", value = "#pu.name"),
			@CacheKey(key = "PHONE", value = "#pu.phoneNumber") }, clazz = PhoenixUser.class)
	@Override
	public int deleteUser(PhoenixUser pu) {
		// TODO Auto-generated method stub
		return phoenixUserMapper.deleteByPrimaryKey(pu.getId());
	}

}
