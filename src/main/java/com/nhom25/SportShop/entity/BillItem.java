package com.nhom25.SportShop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
public class BillItem {
	@Id
	@Column(name = "BillItemId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "BillId")
	private Integer billId;
	@Column(name = "ItemId")
	private Integer itemId;
	@Column(name = "Quantity", nullable = false)
	private Short quantity;
	@Column(name = "Price", nullable = false)
	private Integer price;
}
