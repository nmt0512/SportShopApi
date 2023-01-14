package com.nhom25.SportShop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class GeneralCategory {
	@Id
	@Column(name = "GeneralId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "Name", nullable = false)
	private String name;
	@Column(name = "Code", nullable = false, unique = true)
	private String code;
}
