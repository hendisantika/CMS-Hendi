package com.hendi.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */

@Entity
@Table(name = "tbl_roles")
public class Roles {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String role;

	@ManyToOne
	@JoinColumn(name = "userid")
	private Users users;

	public Long getId() {
		return id;
	}

	public void setId(Long pId) {
		id = pId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String pRole) {
		role = pRole;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users pUsers) {
		users = pUsers;
	}
}