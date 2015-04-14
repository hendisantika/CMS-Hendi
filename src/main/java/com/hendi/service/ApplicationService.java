package com.hendi.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hendi.domain.Users;
import com.hendi.utils.Constant;


/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */

@Service(value = "userDetailsService")
public class ApplicationService implements UserDetailsService {
	private static final String USERNAME = "username";

	@Autowired
	private IDatabaseService databaseService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Map parameters = new HashMap();
		parameters.put(USERNAME, username);

		List users = databaseService.findUsers(parameters);
		if (users.size() == 0) {
			throw new UsernameNotFoundException("User Not Found!");
		}

		Users usersEntity = (Users) users.get(0);
		boolean enabled = Constant.IS_ENABLED;
		boolean accountNonExpired = Constant.ACC_NOT_EXPIRED;
		boolean credentialsNonExpired = Constant.CREDENTIAL_NOT_EXPIRED;
		boolean accountNonLocked = Constant.ACC_NOT_LOCKED;

		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(usersEntity.getRoles().getRole()));

		User user = new User(username, usersEntity.getPassword(), enabled,
							 accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		return user;
	}
}