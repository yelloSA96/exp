package com.lutoke.pond.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lutoke.pond.repository.EmployeeRepository;
import com.lutoke.pond.security.*;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api")
@Log4j2
public class LoginController {
	@Autowired
	EmployeeRepository employeeRepo;
	
	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	EmployeeDetailsService eDetailsService;
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody JwtRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
		final EmployeeDetails userDetails = (EmployeeDetails) eDetailsService.loadUserByUsername(authenticationRequest.getEmail());
		final String token = jwtTokenUtil.generateToken(userDetails);
		log.trace("User successfully logged in with email " + authenticationRequest.getEmail());
		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate(String email, String password) throws Exception {
		log.trace("User tried to login with email " + email);
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		} catch (DisabledException e) {
			log.warn("User account is disabled");
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			log.info("User entered bad credentials");
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
