package com.nhom25.SportShop.repository;

import com.nhom25.SportShop.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findByUsername(String username);
    Cart findByUsernameAndItemId(String username, Integer itemId);
}
