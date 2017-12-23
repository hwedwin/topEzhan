package com.topaiebiz.sms.dahantc.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.List;

@Component
public class RedisClient {

	@Resource
	public JedisPool pool;

	public RedisClient(JedisPool pool) {
		super();
		this.pool = pool;
	}

	
	public void set(String key, Object value){
		Jedis jedis = pool.getResource();
		try {
			if(value instanceof String){
				jedis.set(key, (String)value);
			}else{
				jedis.set(key, JSON.toJSONString(value));
			}
		} catch (Exception e) {
			throw new RuntimeException("jedis异常", e);
		}finally {
			if(jedis != null){
				jedis.close();
			}
		}
	}
	
	public void set(String key, Object value, int seconds){
		Jedis jedis = pool.getResource();
		try {
			if(value instanceof String){
				jedis.setex(key, seconds, (String)value);
			}else{
				jedis.setex(key, seconds, JSON.toJSONString(value));
			}
		} catch (Exception e) {
			throw new RuntimeException("jedis异常", e);
		}finally {
			if(jedis != null){
				jedis.close();
			}
		}
	}
	
	public String get(String key){
		Jedis jedis = pool.getResource();
		try {
			return jedis.get(key);
		} catch (Exception e) {
			throw new RuntimeException("jedis异常", e);
		}finally {
			if(jedis != null){
				jedis.close();
			}
		}
	}
	
	public <T> T get(String key, Class<T> clazz){
		Jedis jedis = pool.getResource();
		try {
			String value = jedis.get(key);
			if(StringUtils.isNotBlank(value)){
				return JSON.parseObject(value, clazz);
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException("jedis异常", e);
		}finally {
			if(jedis != null){
				jedis.close();
			}
		}
	}
	
	public <T> List<T> getList(String key, Class<T> clazz){
		Jedis jedis = pool.getResource();
		try {
			String value = jedis.get(key);
			if(StringUtils.isNotBlank(value)){
				return JSON.parseArray(value, clazz);
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException("jedis异常", e);
		}finally {
			if(jedis != null){
				jedis.close();
			}
		}
	}
	
	public <T> T getObject(String key, TypeReference<T> typeRef){
		Jedis jedis = pool.getResource();
		try {
			String value = jedis.get(key);
			if(StringUtils.isNotBlank(value)){
				return JSON.parseObject(value, typeRef);
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException("jedis异常", e);
		}finally {
			if(jedis != null){
				jedis.close();
			}
		}
	}
	
	public Long delete(String key){
		Jedis jedis = pool.getResource();
		try {
			return jedis.del(key);
		} catch (Exception e) {
			throw new RuntimeException("jedis异常", e);
		}finally {
			if(jedis != null){
				jedis.close();
			}
		}
	}
	
	public Long incr(String key){
		Jedis jedis = pool.getResource();
		try {
			return jedis.incr(key);
		} catch (Exception e) {
			throw new RuntimeException("jedis异常", e);
		}finally {
			if(jedis != null){
				jedis.close();
			}
		}
	}
	
	public Long incr(String key, long value){
		Jedis jedis = pool.getResource();
		try {
			return jedis.incrBy(key, value);
		} catch (Exception e) {
			throw new RuntimeException("jedis异常", e);
		}finally {
			if(jedis != null){
				jedis.close();
			}
		}
	}
	
	public Long expire(String key, int seconds){
		Jedis jedis = pool.getResource();
		try {
			return jedis.expire(key, seconds);
		} catch (Exception e) {
			throw new RuntimeException("jedis异常", e);
		}finally {
			if(jedis != null){
				jedis.close();
			}
		}
	}
	
	public boolean exists(String key){
		Jedis jedis = pool.getResource();
		try {
			return jedis.exists(key);
		} catch (Exception e) {
			throw new RuntimeException("jedis异常", e);
		}finally {
			if(jedis != null){
				jedis.close();
			}
		}
	}
	
	/**
	 * 
	 * @param key
	 * @param value
	 * @return 1成功，0失败
	 */
	public long setNx(String key, Object value){
		Jedis jedis = pool.getResource();
		try {
			return jedis.setnx(key, JSON.toJSONString(value));
		} catch (Exception e) {
			throw new RuntimeException("jedis异常", e);
		}finally {
			if(jedis != null){
				jedis.close();
			}
		}
	}
	
	public boolean tryLock(String key, int seconds){
		Jedis jedis = pool.getResource();
		try {
			return "OK".equals(jedis.set(key, "lock", "nx", "ex", seconds));
//			if(jedis.setnx(key, "lock") == 1){
//				jedis.expire(key, seconds);
//				return true;
//			}else{
//				return false;
//			}
		} catch (Exception e) {
			throw new RuntimeException("jedis异常", e);
		}finally {
			if(jedis != null){
				jedis.close();
			}
		}
	}
	
	public boolean lock(String key){
		for(int i = 0; i < 10; i++){
			if(tryLock(key, 30)){
				return true;
			}else{
				try {
					Thread.sleep(200l);
				} catch (InterruptedException e) {
					return false;
				}
			}
		}
		return false;
	}
	
	public void unlock(String key){
		delete(key);
	}
	
	public boolean isErrorLock(String key, int maxTimes){
		Jedis jedis = pool.getResource();
		try {
			Integer value = get(key, Integer.class);
			return value != null && value > maxTimes;
		} catch (Exception e) {
			throw new RuntimeException("jedis异常", e);
		}finally {
			if(jedis != null){
				jedis.close();
			}
		}
	}
	
	public void addErrorLock(String key, int seconds){
		Jedis jedis = pool.getResource();
		try {
			long value = jedis.incr(key);
			if(value == 1){
				jedis.expire(key, seconds);	
			}
		} catch (Exception e) {
			throw new RuntimeException("jedis异常", e);
		}finally {
			if(jedis != null){
				jedis.close();
			}
		}
	}
	
	public Jedis getJedis(){
		return pool.getResource();
	}
	
	public void close(Jedis jedis){
		if(jedis != null){
			jedis.close();
		}
	}
	
	public JedisPool getPool() {
		return pool;
	}

}
