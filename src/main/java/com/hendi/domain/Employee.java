package com.hendi.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */

@Entity
@Table(name = "tbl_employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String gender;

	private String phone;
	private String email;

	@Temporal(TemporalType.DATE)
	private Date birthdate;

	public Long getId() {
		return id;
	}

	public void setId(Long pId) {
		id = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String pName) {
		name = pName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String pGender) {
		gender = pGender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String pPhone) {
		phone = pPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String pEmail) {
		email = pEmail;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date pBirthdate) {
		birthdate = pBirthdate;
	}
}