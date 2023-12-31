package com.suresh.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.suresh.entity.User;
import com.suresh.repository.UserRepository;

@Service
public class CustomUserDetails implements UserDetailsService {

	UserRepository userRepository;

	CustomUserDetails(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User dbUser = userRepository.findByUserNameOrEmail(username, username)
				.orElseThrow(() -> new UsernameNotFoundException(username));

		// convert DB user to Spring security user (which requires username password and
		// authorities)

		// convert roles to authorities
		Set<SimpleGrantedAuthority> authorities = dbUser.getRoles().stream()
				.map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());

		return new org.springframework.security.core.userdetails.User(dbUser.getUserName(), dbUser.getPassword(),
				authorities);
	}

}
