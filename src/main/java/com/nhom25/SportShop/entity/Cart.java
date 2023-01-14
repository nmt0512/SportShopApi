package com.nhom25.SportShop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Cart {
    @Id
    @Column(name = "CartId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "ItemId")
    private Integer itemId;
    @Column(name = "Username", nullable = false)
    private String username;
    @Column(name = "Quantity", nullable = false)
    private Short quantity;
    @Column(name = "Price", nullable = false)
    private Integer price;
}
