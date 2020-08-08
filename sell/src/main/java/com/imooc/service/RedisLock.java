package com.imooc.service;

import lombok.extern.slf4j.Slf4j;
import org.simpleframework.xml.core.Commit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @program: sell
 * @description
 * @author: Tian
 * @create: 2020-08-04 21:50
 **/
@Component
@Slf4j
public class RedisLock {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * add lock
     * @param key
     * @param value current time + over time
     * @return
     */
    public boolean lock(String key, String value) {
        if(redisTemplate.opsForValue().setIfAbsent(key, value)){
            return true;
        }

        /* only one thread can own the lock, suppose current value is A; two thread value is B.
           if one thread run the code 42 line, the oldValue should be A and it can get the lock.
           after that another thread run the 42 line and get the old Value, but this time, the old value should
           be B, and currentValue = A != B, so can not get the lock **/
        String currentValue = redisTemplate.opsForValue().get(key);
        //if lock expired
        if (!StringUtils.isEmpty(currentValue)
                && Long.parseLong(currentValue) < System.currentTimeMillis()) {

            //get previous lock time
            String oldValue = redisTemplate.opsForValue().getAndSet(key, value);
            if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)) {
                return true;
            }
        }
        return false;
    }

    /**
     * unlock
     * @param key
     * @param value
     */
    public void unlock(String key, String value) {
        try {
            String currentValue = redisTemplate.opsForValue().get(key);
            if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        }catch (Exception e){
            log.error("[redis distributed lock] unlock error, {}", e);
        }
    }
}
