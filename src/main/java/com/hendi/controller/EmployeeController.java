package com.hendi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hendi.domain.Employee;
import com.hendi.service.IDatabaseService;


/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */

@Controller
@RequestMapping("/master/employee*")
public class EmployeeController {
	private static final Logger LOG = Logger.getLogger(EmployeeController.class.getSimpleName());

	private static final String RESPONSE = "response";
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private IDatabaseService databaseService;

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@ResponseBody
	public List<Employee> search() {
		return databaseService.findAllEmployee();
	}

	@RequestMapping(value = "/saveorupdate", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveorupdate(@RequestBody Employee employee) {
		Map<String, String> jsonObject = new HashMap<String, String>();
		try {
			databaseService.saveorUpdateEmployee(employee);
			jsonObject.put(RESPONSE, SUCCESS);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			jsonObject.put(RESPONSE, FAIL);
		}

		return jsonObject;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(@RequestBody Employee employee) {
		Map<String, String> jsonObject = new HashMap<String, String>();
		try {
			databaseService.deleteEmployee(employee);
			jsonObject.put(RESPONSE, SUCCESS);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			jsonObject.put(RESPONSE, FAIL);
		}

		return jsonObject;
	}
}