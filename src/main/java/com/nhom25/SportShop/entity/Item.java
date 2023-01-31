package com.nhom25.SportShop.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Item implements Serializable {
	@Id
	@Column(name = "ItemId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "Code", unique = true, nullable = false)
	private String code;
	@Column(name = "Name", nullable = false)
	private String name;
	@Column(name = "Description")
	private String description;
	@Column(name = "CategoryCode")
	private String categoryCode;
	@Column(name = "Size")
	private String size;
	@Column(name = "Color")
	private String color;
	@Column(name = "Type")
	private String type;
	@Column(name = "Quantity", nullable = false)
	private Short quantity;
	@Column(name = "Price")
	private Integer price;
	@Column(name = "CreatedDate")
	private Timestamp createdDate;
}
