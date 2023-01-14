package com.nhom25.SportShop.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Bill {
	@Id
	@Column(name = "BillId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "TotalPrice", nullable = false)
	private Integer totalPrice;
	@Column(name = "Time", nullable = false)
	private Timestamp time;
	@Column(name = "Username")
	private String username;
	@Column(name = "Confirm", nullable = false)
	private Boolean confirm;
	@Column(name = "Status", nullable = false)
	private Boolean status;

}
