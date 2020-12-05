package com.lutoke.pond.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import com.lutoke.pond.model.employee.Employee;
import com.lutoke.pond.repository.EmployeeRepository;
import com.lutoke.pond.security.EmployeeDetailsService;
import com.lutoke.pond.security.JwtRequest;
import com.lutoke.pond.security.JwtResponse;
import com.lutoke.pond.security.JwtTokenUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
public class LoginTest {
	
	@Mock 
	EmployeeRepository mockRepo;
	
	@Mock
	Employee mockEmployee;
	
	@Mock
	AuthenticationManager mockAManager;
	
	@Mock
	JwtRequest mockRequest;
	
	
	
	@Mock
	EmployeeDetailsService mockEDS;
	
	@Mock
	JwtTokenUtil jwtTokenUtil;
	
	@InjectMocks
	LoginController controller = new LoginController();


	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test(expected = Exception.class)
	public void login_throws_for_empty_email() throws Exception {
		String badEmail = "";
		when(mockAManager.authenticate(any())).thenThrow(new Exception());
		controller.login(new JwtRequest(badEmail,"pword"));
	}
	
	
	@Test
	public void login_happy_for_admin_mcadmin() throws Exception {
		String email = "admin@notfdm.com";
		String pword = "p";
		when(mockRequest.getEmail()).thenReturn(email);
		when(mockRequest.getPassword()).thenReturn(pword);
//		when(mockAManager.authenticate(any()));
		ResponseEntity<?> result =controller.login(mockRequest); 
		assertEquals(result.getStatusCode(),HttpStatus.OK);
	}
	
}
