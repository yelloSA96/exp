package com.lutoke.pond.controller;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.*;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringRunner.class)
@WebMvcTest(RegisterEmployeeController.class)
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RegistrationTest {
	
	@Autowired
	private WebApplicationContext context;
	private MockMvc mvc;

	@WithMockUser(value = "testDumdum")
	@Test(expected = Exception.class)
	@Ignore
	public void registration_fails_for_unauthorised_registrar() throws Exception {
//		mvc.perform(get("/private/hello").contentType(MediaType.APPLICATION_JSON))
//        .andExpect(status().isOk());
	}
	
}
