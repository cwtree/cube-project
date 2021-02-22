package com.cube.service.impl;

import org.springframework.stereotype.Service;

import com.cube.service.PayService;

/**
 * 
 * 
 * @author phoenix
 * @date 2021-2-22
 */
@Service
public class WeXinPayServiceImpl implements PayService {

	@Override
	public String pay(String pay) {
		// TODO Auto-generated method stub
		return "微信支付";
	}

	@Override
	public String type() {
		// TODO Auto-generated method stub
		return "weixin";
	}

}
