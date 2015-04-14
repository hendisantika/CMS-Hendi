package com.hendi.service;

import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hendi.domain.Employee;
import com.hendi.domain.Roles;
import com.hendi.domain.Users;


/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */

@Service(value = "iDatabaseService")
@Transactional
public class DatabaseService implements IDatabaseService {
	@Autowired
	protected SessionFactory sessionFactory;

	@Autowired
	private BaseService baseService;

	@Override
	public List findUsers(Map<String, Object> parameters) {
		return baseService.runHQL("from Users where username = :username", parameters);
	}

	@Override
	public List findAllUsers(Map<String, Object> parameters) {
		return baseService.runHQL("from Roles where role != :role", parameters);
	}

	@Override
	public void saveorUpdateUsers(Users users, Roles roles) {
		sessionFactory.getCurrentSession().saveOrUpdate(users);
		sessionFactory.getCurrentSession().saveOrUpdate(roles);
	}

	@Override
	public void deleteUsers(Users users) {
		sessionFactory.getCurrentSession().delete(users);
	}

	@Override
	public List findAllEmployee() {
		return sessionFactory.getCurrentSession().createQuery("from Employee").list();
	}

	@Override
	public void saveorUpdateEmployee(Employee employee) {
		sessionFactory.getCurrentSession().saveOrUpdate(employee);
	}

	@Override
	public void deleteEmployee(Employee employee) {
		sessionFactory.getCurrentSession().delete(employee);
	}
}