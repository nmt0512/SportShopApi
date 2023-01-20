package com.nhom25.SportShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhom25.SportShop.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Integer>{
    User findByUsername(String username);
    User findByUsernameOrEmail(String username, String email);
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
    Boolean existsByUsernameAndPassword(String username, String password);

    @Modifying
    @Transactional
    @Query(value = "UPDATE DBUser SET Password = :password WHERE Username = :username", nativeQuery = true)
    void updateUserPassword(@Param("password") String password, @Param("username") String username);

    @Modifying
    @Transactional
    @Query(value = "UPDATE DBUser SET Address = :address WHERE Username = :username", nativeQuery = true)
    void updateUserAddress(@Param("username") String username, @Param("address") String address);
}
