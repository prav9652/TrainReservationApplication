package com.hcl.ticketbooking;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.hcl.ticketbooking.service.UserService;
import com.hcl.ticketbooking.service.impl.UserServiceImpl;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@Import(TestConfig.class)
public class TicketBookingUserControllerTestCases {
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	static UserDetailsService userDetailsService;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private MockMvc mvc;
//	@Autowired
//	private RestTemplate restTemplate;
	@Autowired
	TestRestTemplate restTemplate;
//
//	@BeforeClass
//	public static void setup() {
//		// Create application context instance
//		//applicationContext = new ClassPathXmlApplicationContext("application-security.xml");
//		// Get user details service configured in configuration
//		System.out.println("hi");
//		//userDetailsService = applicationContext.getBean(UserServiceImpl.class);
//	}

	@Test
	public void testValidRole() throws Exception {
		// Get the user by user name from configured user details service
//		UserDetails userDetails = userDetailsService.loadUserByUsername("lpk");
//		Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
//				userDetails.getPassword(), userDetails.getAuthorities());
//		SecurityContextHolder.getContext().setAuthentication(authToken);
		String url = "https://localhost:8081/users";
		URI uri = new URI(url);
		final String token = jwtTokenUtil.generateToken(
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("lpk", "lpk")));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(url).header("Authorization",token);
		 mvc.perform(requestBuilder).andExpect(status().isOk());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", token);
		HttpEntity<String> request = new HttpEntity<String>("parameters", headers);
		//ResponseEntity<String> response = restTemplate.withBasicAuth("lpkumar", "lpkumar").getForEntity(uri,String.class);
//		ResponseEntity<String> responses = restTemplate.exchange(
//		        url,
//		        HttpMethod.GET,
//		        request,
//		        String.class,
//		        1
//		);
//		 assertEquals(HttpStatus.OK, responses.getStatusCode());
	}

	@Test
	public void testCheckout() {

		Assert.assertEquals("r", "r");
	}
}
