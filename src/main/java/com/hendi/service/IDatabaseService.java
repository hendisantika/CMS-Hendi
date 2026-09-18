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
	List<Users> findUsers(Map<String, Object> parameters);

	List<Roles> findAllUsers(Map<String, Object> parameters);

	void saveorUpdateUsers(Users users, Roles roles);

	void deleteUsers(Users users);

	List<Employee> findAllEmployee();

	void saveorUpdateEmployee(Employee employee);

	void deleteEmployee(Employee employee);
}
