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
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put(USERNAME, username);

		List<Users> users = databaseService.findUsers(parameters);
		if (users.isEmpty()) {
			throw new UsernameNotFoundException("User Not Found!");
		}

		Users usersEntity = users.get(0);
		boolean enabled = Constant.IS_ENABLED;
		boolean accountNonExpired = Constant.ACC_NOT_EXPIRED;
		boolean credentialsNonExpired = Constant.CREDENTIAL_NOT_EXPIRED;
		boolean accountNonLocked = Constant.ACC_NOT_LOCKED;

		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(usersEntity.getRoles().getRole()));

		return new User(username, usersEntity.getPassword(), enabled,
						accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}
}
