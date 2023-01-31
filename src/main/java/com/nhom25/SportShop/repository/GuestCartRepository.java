package com.nhom25.SportShop.repository;

import com.nhom25.SportShop.entity.GuestCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

import java.util.List;

@Repository
public class GuestCartRepository {
    public static final String HASH_KEY_NAME = "GUEST-CART";
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private Jedis jedis;

    public GuestCart save(GuestCart guestCart)
    {
        redisTemplate.opsForHash().put(HASH_KEY_NAME, guestCart.getId(), guestCart);
        return findById(guestCart.getId());
    }

    public List<GuestCart> findAll()
    {
        return redisTemplate.opsForHash().values(HASH_KEY_NAME);
    }

    public GuestCart findById(Integer id)
    {
        return (GuestCart) redisTemplate.opsForHash().get(HASH_KEY_NAME, id);
    }

    public Boolean deleteById(Integer id)
    {
        redisTemplate.opsForHash().delete(HASH_KEY_NAME, id);
        return true;
    }

    public void deleteAll()
    {
        jedis.flushAll();
    }
}
