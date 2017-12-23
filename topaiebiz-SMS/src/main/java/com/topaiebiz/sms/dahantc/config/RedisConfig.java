package com.topaiebiz.sms.dahantc.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by Luoqy on 2017/12/12.
 * desc:
 */
@Configuration
public class RedisConfig {

    @Bean("jedis.config")
    public JedisPoolConfig jedisPoolConfig(//
                                           @Value("${jedis.pool.min-idle}") int minIdle, //
                                           @Value("${jedis.pool.max-idle}") int maxIdle, //
                                           @Value("${jedis.pool.max-wait}") int maxWaitMillis, //
                                           @Value("${jedis.pool.block-when-exhausted}") boolean blockWhenExhausted, //
                                           @Value("${jedis.pool.max-total}") int maxTotal) {

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMinIdle(minIdle);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        config.setMaxTotal(maxTotal);
        // 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
        config.setBlockWhenExhausted(blockWhenExhausted);
        // 是否启用pool的jmx管理功能, 默认true
        config.setJmxEnabled(true);
        return config;
    }

    @Bean
    public JedisPool jedisPool(
                               @Qualifier("jedis.config") JedisPoolConfig config,
                               @Value("${jedis.host}") String host,
                               @Value("${jedis.port}") int port, @Value("${jedis.password}") String password) {
        // (GenericObjectPoolConfig poolConfig, String host, int port, int timeout, String password, int database)
        return new JedisPool(config, host, port, 2000, password,0);
    }
}