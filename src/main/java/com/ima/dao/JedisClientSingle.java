package com.ima.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by ${符柱成} on 2017/3/28.
 */
public class JedisClientSingle implements JedisClient {

//    @Autowired
//    private JedisPool jedisPool;
    @Bean
    public JedisPool getJedisPool(){
        return new JedisPool("127.0.0.1",6379);
    }
    @Override
    public String get(String key) {
        Jedis jedis = getJedisPool().getResource();
        String string = jedis.get(key);
        jedis.close();
        return string;
    }

    @Override
    public String set(String key, String value) {
        Jedis jedis = getJedisPool().getResource();
        String string = jedis.set(key, value);
        jedis.close();
        return string;
    }

    @Override
    public String hget(String hkey, String key) {
        System.out.println("jedisPool   "+getJedisPool());
        Jedis jedis = getJedisPool().getResource();
        System.out.println("jedis   "+jedis);
        String string = jedis.hget(hkey, key);
        jedis.close();
        return string;
    }

    @Override
    public long hset(String hkey, String key, String value) {
        Jedis jedis = getJedisPool().getResource();
        Long result = jedis.hset(hkey, key, value);
        jedis.close();
        return result;
    }

    @Override
    public long incr(String key) {
        Jedis jedis = getJedisPool().getResource();
        Long result = jedis.incr(key);
        jedis.close();
        return result;
    }

    @Override
    public long expire(String key, int second) {
        Jedis jedis = getJedisPool().getResource();
        Long result = jedis.expire(key, second);
        jedis.close();
        return result;
    }

    @Override
    public long ttl(String key) {
        Jedis jedis = getJedisPool().getResource();
        Long result = jedis.ttl(key);
        jedis.close();
        return result;
    }

    @Override
    public long del(String key) {
        Jedis jedis = getJedisPool().getResource();
        Long result = jedis.del(key);
        jedis.close();
        return result;
    }

    @Override
    public long hdel(String hkey, String key) {
        Jedis jedis = getJedisPool().getResource();
        Long result = jedis.hdel(hkey,key);
        jedis.close();
        return result;
    }
}