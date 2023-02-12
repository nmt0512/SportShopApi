package com.nhom25.SportShop.repository;

import com.nhom25.SportShop.entity.GuestCart;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Repository
public class GuestCartRepository {
    @Resource(name="redisTemplate")
    private HashOperations<String, Integer, GuestCart> hashOperations;

    public void save(String sessionId, GuestCart guestCart)
    {
        hashOperations.putIfAbsent(sessionId, guestCart.getId(), guestCart);
    }

    public void saveAll(String sessionId, Map<Integer, GuestCart> guestCartMap)
    {
        hashOperations.putAll(sessionId, guestCartMap);
    }

    public void update(String sessionId, GuestCart guestCart)
    {
        hashOperations.put(sessionId, guestCart.getId(), guestCart);
    }

    public List<GuestCart> findAll(String sessionId)
    {
        return hashOperations.values(sessionId);
    }

    public GuestCart findById(String sessionId, Integer id)
    {
        return hashOperations.get(sessionId, id);
    }

    public void deleteById(String sessionId, Integer id) {
        hashOperations.delete(sessionId, id);
    }
    public void deleteAll(String sessionId)
    {
        hashOperations.delete(sessionId);
    }
}
