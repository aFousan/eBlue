/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afousan.eblue.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Data-tier,
 *
 * @author aFousan
 */
@Configuration
@ComponentScan("com.afousan")
@PropertySource({"classpath:/com/afousan/eblue/properties/redis.properties"})
public class DataConfig {

    @Autowired
    Environment env;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        System.out.println("FOusan Redis Template" + env.getProperty("redis.host"));
        JedisConnectionFactory jedis = new JedisConnectionFactory();
        jedis.setHostName("localhost");
        jedis.setPort(6379);
        jedis.setPassword("");
        jedis.setUsePool(true);
        return jedis;
    }

    @Bean
    public StringRedisTemplate redisTemplate() {
        StringRedisTemplate rt = new StringRedisTemplate();
        rt.setConnectionFactory(jedisConnectionFactory());
        return rt;
    }
}
