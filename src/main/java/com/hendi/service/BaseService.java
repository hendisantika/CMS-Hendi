package com.hendi.service;

import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */

@Service(value = "baseService")
public class BaseService {
	@Autowired
	protected SessionFactory sessionFactory;

	public <T> List<T> runHQL(String hql, Class<T> resultType, Map<String, Object> parameters) {
		Query<T> query = sessionFactory.getCurrentSession().createQuery(hql, resultType);

		if (parameters != null) {
			for (String key : parameters.keySet()) {
				query.setParameter(key, parameters.get(key));
			}
		}
		return query.list();
	}
}
