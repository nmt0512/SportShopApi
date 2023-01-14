package com.nhom25.SportShop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
public class Category {
	@Id
	@Column(name = "CategoryId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "Code", unique = true, nullable = false)
	private String code;
	@Column(name = "Name", nullable = false)
	private String name;
	@Column(name = "GeneralCode")
	private String generalCode;
	@Column(name = "CreatedDate")
	private Timestamp createdDate;
}
