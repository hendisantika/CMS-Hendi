package com.hendi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hendi.domain.Roles;
import com.hendi.domain.Users;
import com.hendi.service.IDatabaseService;


/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */

@Controller
@RequestMapping("/master/users*")
public class UsersController {
	private static final Logger LOG = LoggerFactory.getLogger(UsersController.class);

	private static final String ROLE = "role";
	private static final String ADMIN = "ADMIN";
	private static final String RESPONSE = "response";
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private IDatabaseService databaseService;

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@ResponseBody
	public List<Roles> search() {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put(ROLE, ADMIN);
		return databaseService.findAllUsers(parameters);
	}

	@RequestMapping(value = "/saveorupdate", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveorupdate(@RequestBody Users users) {
		Map<String, String> jsonObject = new HashMap<>();
		try {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			Roles roles = new Roles();
			roles.setRole("USER");
			roles.setUsers(users);
			users.setRoles(roles);
			users.setPassword(passwordEncoder.encode(users.getPassword()));

			databaseService.saveorUpdateUsers(users, roles);
			jsonObject.put(RESPONSE, SUCCESS);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			jsonObject.put(RESPONSE, FAIL);
		}

		return jsonObject;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(@RequestBody Users users) {
		Map<String, String> jsonObject = new HashMap<>();
		try {
			databaseService.deleteUsers(users);
			jsonObject.put(RESPONSE, SUCCESS);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			jsonObject.put(RESPONSE, FAIL);
		}

		return jsonObject;
	}
}