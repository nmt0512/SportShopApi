package com.nhom25.SportShop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Image {
    @Id
    @Column(name = "ImageId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Link")
    private String link;
    @Column(name = "ItemId")
    private Integer itemId;
}
