/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afousan.eblue.init;

import javax.annotation.Resource;
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
@PropertySource("classpath:redis.properties")
public class DataConfig {

    @Resource
    private Environment env;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedis = new JedisConnectionFactory();
        jedis.setHostName(env.getProperty("redis.host"));
        jedis.setPort(Integer.parseInt(env.getProperty("redis.port")));
        jedis.setPassword(env.getProperty("redis.pass"));
        jedis.setUsePool(true);
        return jedis;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate() {
        StringRedisTemplate rt = new StringRedisTemplate();
        rt.setConnectionFactory(jedisConnectionFactory());
        return rt;
    }
}
