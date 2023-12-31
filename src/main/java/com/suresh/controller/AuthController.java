package com.suresh.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suresh.dto.LoginDto;
import com.suresh.service.AuthService;

@RestController
@RequestMapping("api/auth")
public class AuthController {

	private AuthService authService;

	AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping(value = { "/login", "/signin" })
	public ResponseEntity<String> login(LoginDto loginDto) {
		String response = authService.login(loginDto);
		return ResponseEntity.ok(response);
	}
}
