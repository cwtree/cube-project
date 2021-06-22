package com.cube.listener;

import javax.annotation.Resource;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 
 * @author phoenix
 * @date 2021-3-20
 */
@Component
@Slf4j
public class KeyExpiredListener extends KeyExpirationEventMessageListener {

	@Resource(name = "myRedisTemplate")
	private RedisTemplate<String, String> redisTemplate;

	public KeyExpiredListener(RedisMessageListenerContainer listenerContainer) {
		super(listenerContainer);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onMessage(Message message, byte[] pattern) {
		String key = StrUtil.utf8Str(message.getBody());
		if (log.isInfoEnabled()) {
			log.info("监听到key过期事件 key = {} channel = {} pattern = {}", key, StrUtil.utf8Str(message.getChannel()),
					StrUtil.utf8Str(pattern));
		}
		// TODO key满足一定规则，则表示是下订单时用于后期处理订单超时未支付的key，则直接在这里修改订单状态关闭
		// 当然该方法还需要一个兜底的方案，防止缓存数据丢失，需要一个定时任务，5分钟一轮去库里捞一遍超过30分钟未处理的订单，修改状态
		//防止重复消费处理，需要加锁
		//利用redis setIfAbsent命令,如果为空set返回true,如果不为空返回false,类似setnx加锁操作
        //Boolean aBoolean = stringRedisTemplate.opsForValue().setIfAbsent(SET_NX + expiredKey, String.valueOf(System.currentTimeMillis()),10, TimeUnit.SECONDS);
	}

}
