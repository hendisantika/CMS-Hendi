package com.hendi.controller;

import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hendi.domain.Users;


/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */

@Controller
public class ApplicationController {
	private static final Logger LOG = Logger.getLogger(ApplicationController.class.getSimpleName());

	private static final String URI_LOGIN = "login";
	private static final String URI_USERS = "master/users";
	private static final String URI_EMPLOYEE = "master/employee";
	private static final String URI_REDIRECT_LOGIN = "redirect:/login";
	private static final String URI_REDIRECT_USERS = "redirect:/master/users";
	private static final String URI_REDIRECT_EMPLOYEE = "redirect:/master/employee";
	private static final String ROLE_ADMIN = "ADMIN";
	private static final String ROLE_USERS = "USER";

	@Resource(name = "authenticationManager")
	private AuthenticationManager authenticationManager;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String open(HttpSession httpSession) {
		Authentication applicationAuthentication = SecurityContextHolder.getContext().getAuthentication();

		Set<String> roles = AuthorityUtils.authorityListToSet(applicationAuthentication.getAuthorities());
		if (roles.contains(ROLE_ADMIN)) {
			return URI_REDIRECT_USERS;
		} else if (roles.contains(ROLE_USERS)) {
			return URI_REDIRECT_EMPLOYEE;
		} else {
			httpSession.invalidate();
			return URI_REDIRECT_LOGIN;
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String openLogin(ModelMap modelMap) {
		modelMap.put("authentication", true);
		return URI_LOGIN;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String submitLogin(ModelMap modelMap, @ModelAttribute Users userApp) {
		Authentication userAuthentication = new UsernamePasswordAuthenticationToken(userApp.getUsername(), userApp.getPassword());

		try {
			Authentication authentication = authenticationManager.authenticate(userAuthentication);
			SecurityContextHolder.getContext().setAuthentication(authentication);

			Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
			if (roles.contains(ROLE_ADMIN)) {
				return URI_REDIRECT_USERS;
			} else {
				return URI_REDIRECT_EMPLOYEE;
			}
		} catch (AuthenticationException e) {
			LOG.error(e.getMessage(), e);
			modelMap.put("authentication", false);
			return URI_LOGIN;
		}
	}

	@RequestMapping(value = "/master/users", method = RequestMethod.GET)
	public String openUsers() {
		return URI_USERS;
	}

	@RequestMapping(value = "/master/employee", method = RequestMethod.GET)
	public String openEmployee() {
		return URI_EMPLOYEE;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String submitLogout(HttpSession httpSession) {
		httpSession.invalidate();
		return URI_REDIRECT_LOGIN;
	}
}