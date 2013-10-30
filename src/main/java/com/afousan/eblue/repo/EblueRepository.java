/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afousan.eblue.repo;

import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.util.StringUtils;

/**
 *
 * @author aFousan
 */
@Named
public class EblueRepository {

    private final StringRedisTemplate template;
    private final ValueOperations<String, String> valueOps;
    private final RedisAtomicLong userIdCounter;

    @Inject
    public EblueRepository(StringRedisTemplate template) {
        this.template = template;
        valueOps = template.opsForValue();
        userIdCounter = new RedisAtomicLong(RedisKeyUtils.globalUid(), template.getConnectionFactory());
    }

    //add user
    public String addUser(String name, String pass) {
        if (name != null) {
            String uid = String.valueOf(userIdCounter.incrementAndGet());
            BoundHashOperations<String, String, String> userOps = template.boundHashOps(RedisKeyUtils.uid(uid));

            userOps.put("name", name);
            userOps.put("pass", pass);
            valueOps.set(RedisKeyUtils.user(name), uid);
        }
        //add to list

        return addAuth(name);
    }

    public boolean auth(String user, String pass) {
        // find uid
        String uid = findUid(user);
        if (StringUtils.hasText(uid)) {
            BoundHashOperations<String, String, String> userOps = template.boundHashOps(RedisKeyUtils.uid(uid));
            return userOps.get("pass").equals(pass);
        }

        return false;
    }

    public String addAuth(String name) {
        String uid = findUid(name);
        // add random auth key relation
        String auth = UUID.randomUUID().toString();
        valueOps.set(RedisKeyUtils.auth(uid), auth);
        valueOps.set(RedisKeyUtils.authKey(auth), uid);
        return auth;
    }

    public String findUid(String name) {
        return valueOps.get(RedisKeyUtils.user(name));
    }

    public String findNameForAuth(String value) {
        String uid = valueOps.get(RedisKeyUtils.authKey(value));
        return findName(uid);
    }

    private String findName(String uid) {
        if (!StringUtils.hasText(uid)) {
            return "";
        }
        BoundHashOperations<String, String, String> userOps = template.boundHashOps(RedisKeyUtils.uid(uid));
        return userOps.get("name");
    }

    public boolean isUserValid(String name) {
        return template.hasKey(RedisKeyUtils.user(name));
    }
}
