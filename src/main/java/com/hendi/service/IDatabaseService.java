package com.hendi.service;

import java.util.List;
import java.util.Map;

import com.hendi.domain.Employee;
import com.hendi.domain.Roles;
import com.hendi.domain.Users;


/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */
public interface IDatabaseService {
	public List findUsers(Map<String, Object> parameters);

	public List findAllUsers(Map<String, Object> parameters);

	public void saveorUpdateUsers(Users users, Roles roles);

	public void deleteUsers(Users users);

	public List findAllEmployee();

	public void saveorUpdateEmployee(Employee employee);

	public void deleteEmployee(Employee employee);
}