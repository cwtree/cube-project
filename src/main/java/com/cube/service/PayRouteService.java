package com.cube.service;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cube.common.GlobalVar;

import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 
 * @author phoenix
 * @date 2021-2-22
 */
@Service
@Slf4j
public class PayRouteService {

	@Resource
	List<PayService> payServiceList;

	private Map<String, PayService> payServiceMap;

	@PostConstruct
	public void init() {
		payServiceMap = CollUtil.newHashMap();
		for (PayService ps : payServiceList) {
			payServiceMap.put(ps.type(), ps);
		}
		if (log.isInfoEnabled()) {
			log.info("完成支付实现类的map初始化 {}", payServiceMap);
		}
	}

	public String pay(String pay, String type) {
		log.info("==============trace info {}", GlobalVar.THREAD_LOCAL.get());
		GlobalVar.THREAD_LOCAL.remove();
		return payServiceMap.get(type).pay(pay);
	}

}
