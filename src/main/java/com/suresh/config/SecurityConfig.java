package com.suresh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.suresh.jwt.JwtAuthenticationEntryPoint;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	private UserDetailsService userDetails;

	private JwtAuthenticationEntryPoint authenticationEntryPoint;

	public SecurityConfig(UserDetailsService userDetails, JwtAuthenticationEntryPoint authenticationEntryPoint) {
		// TODO Auto-generated constructor stub
		this.userDetails = userDetails;
		this.authenticationEntryPoint = authenticationEntryPoint;
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();

	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf((csrf) -> csrf.disable()).authorizeHttpRequests((authorize) ->
		// authorize.anyRequest().authenticated()) //any request is coming authenticate
		// it.
		authorize.requestMatchers(HttpMethod.GET, "/api/**").permitAll().requestMatchers("/api/auth/**").permitAll()
				.requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll().anyRequest().authenticated())
				.exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		// .httpBasic(Customizer.withDefaults());

		http.headers().frameOptions().disable();
		return http.build();

	}

	/*
	 * @Bean public UserDetailsService userDetailsService() {
	 * 
	 * UserDetails user1 =
	 * User.builder().username("suresh").password(bCryptPasswordEncoder().encode(
	 * "suresh")) .roles("ADMIN").build(); UserDetails user2 =
	 * User.builder().username("naresh").password(bCryptPasswordEncoder().encode(
	 * "naresh")) .roles("USER").build();
	 * 
	 * return new InMemoryUserDetailsManager(user1, user2); }
	 */

}
