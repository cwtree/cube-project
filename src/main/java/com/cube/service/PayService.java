package com.cube.service;

/**
 * 
 * 
 * @author phoenix
 * @date 2021-2-22
 */
public interface PayService {

	/**
	 * 支付实现
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param pay
	 * @return
	 */
	String pay(String pay);
	
	/**
	 * 支付类型
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 */
	String type();
	
}
