package com.afabao.itdragon.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 啊发包
 * @Date: 2019/07/29 2019-07-29
 */

@Configuration
@PropertySource("classpath:redis.properties")
public class RedisSpringConfig {

    @Value("${redis.maxTotal}")
    private Integer redisMaxTotal;

    @Value("${redis.node.port}")
    private Integer redisNodePort;

    @Value("${redis.node.host}")
    private String redisNodeHost;

    private JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(redisMaxTotal);
        return jedisPoolConfig;
    }

    @Bean
    /**省略第一个参数则是采用Protocol.DEFAULT_DATABASE*/
    public JedisPool jedisPool(){
        JedisPool jedisPool = new JedisPool(jedisPoolConfig(),redisNodeHost,redisNodePort);
        return jedisPool;
    }

    @Bean
    public ShardedJedisPool shardedJedisPool(){
        List<JedisShardInfo> jedisShardInfos = new ArrayList<JedisShardInfo>();
        jedisShardInfos.add(new JedisShardInfo(redisNodeHost,redisNodePort));
        return new ShardedJedisPool(jedisPoolConfig(),jedisShardInfos);
    }
}
