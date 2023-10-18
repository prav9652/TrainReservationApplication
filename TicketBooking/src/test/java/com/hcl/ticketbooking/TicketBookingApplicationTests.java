//
//package com.hcl.ticketbooking;
//
//import static org.junit.Assert.assertEquals;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.assertj.core.util.Arrays;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.context.WebApplicationContext;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.hcl.ticketbooking.entity.User;
//import com.hcl.ticketbooking.repository.UserRepository;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class TicketBookingApplicationTests {
//
//	@Autowired
//	WebApplicationContext webApplicationContext;
//
//	@MockBean
//	private UserRepository userRepository;
//
//	protected MockMvc mvc;
//
////	User USER_1 = new User(7L, "praveen", "password", "praveen@hcl.com", new
////  BigDecimal(24), "9652603778", "SoftWare Dev"); // User USER_2 = new User(2l,
////	"David Landup",27,"New York USA"); // User USER_3 = new User(3l,
////	"Jane Doe",31,"New York USA");
//
//	@Before
//	public void setUp() {
//		this.mvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
//	}
//
//	@Test 
//	public void testUpdateUserSuccess() throws Exception { 
//		int status = 0;
//  
//  
//  String url = "/users/1"; 
// // User updatedUser = new User("praveen", "lpk","praveen@hcl.com", new BigDecimal(20), "9652603778","Dev");
//  
//  ResponseEntity<?> responseEntity = new RestTemplate().put(url, User.class,
//  new Long("3"));
//  "http://localhost:8091/currency-exchange/from/{from}/to/{to}", 2);
//  
//  Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(USER_1));
//  Mockito.when(userRepository.save(Mockito.anyObject())).thenReturn(updatedUser);
//  
//  ArrayList<User> li = new ArrayList<User>(Arrays.asList(new User[] {new
//  User("praveen", "lpk", "praveen@hcl.com", new BigDecimal(20), "9652603778",
//  "SoftWare Dev")}));
//  Integer[] crunchifyArray = { 5, 7, 9 }; List<Integer>
//  crunchifyList1, crunchifyList2 = null;  
//  crunchifyList1 = new ArrayList<Integer>(Arrays.asList(crunchifyArray));
//  
//  li.add(new User("praveen", "lpk", "praveen@hcl.com", new BigDecimal(20),
//  "9652603778", "SoftWare Dev"));
//  
//  String inst = new ObjectMapper().writeValueAsString(updatedUser); 
//  MvcResult  mvcResult = mvc.perform(MockMvcRequestBuilders.put(url).contentType(MediaType.APPLICATION_JSON_VALUE).content(inst)).andReturn();
//  
//  status = mvcResult.getResponse().getStatus();
//  System.out.println(mvcResult.getResponse().getContentAsString());
//  assertEquals(201, status); 
//  }
//}