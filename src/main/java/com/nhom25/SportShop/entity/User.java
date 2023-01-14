package com.nhom25.SportShop.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "DBUser")
public class User {
	@Id
	@Column(name = "UserId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "Username", unique = true, nullable = false)
	private String username;
	@Column(name = "Password", nullable = false)
	private String password;
	@Column(name = "Role", nullable = false)
	private Boolean role;
	@Column(name = "Name", nullable = false)
	private String name;
	@Column(name = "Gender")
	private Boolean gender;
	@Column(name = "Address", nullable = false)
	private String address;
	@Column(name = "Dob")
	private Timestamp dob;
	@Column(name = "Phone", nullable = false)
	private String phone;
	@Column(name = "Email", nullable = false)
	private String email;
	@Column(name = "Avatar")
	private String avatar;
}
