package com.hcl.ticketbooking.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ticketbooking.JwtTokenUtil;
import com.hcl.ticketbooking.dto.UserDTO;
import com.hcl.ticketbooking.entity.User;
import com.hcl.ticketbooking.response.MessageResponse;
import com.hcl.ticketbooking.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.service.ResponseMessage;

/**
 * @author yash.ghawghawe
 *
 */
@Api()
@RestController
@RequestMapping("/users")
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	/*
	 * @ApiOperation(value = "Create Post REST API")
	 * 
	 * @RequestMapping(value = "/authenticate", method = RequestMethod.POST) public
	 * ResponseEntity<?> createAuthenticationToken(@RequestBody UserDTO
	 * authenticationRequest) throws Exception {
	 * 
	 * authenticate(authenticationRequest.getUserName(),
	 * authenticationRequest.getPassword());
	 * 
	 * final UserDetails userDetails =
	 * userDetailsService.loadUserByUsername(authenticationRequest.getUserName());
	 * 
	 * final String token = jwtTokenUtil.generateToken(userDetails);
	 * 
	 * return ResponseEntity.ok(token); }
	 * 
	 * 
	 * 
	 * private void authenticate(String username, String password) throws Exception
	 * { try { authenticationManager.authenticate(new
	 * UsernamePasswordAuthenticationToken(username, password)); } catch
	 * (DisabledException e) { throw new Exception("USER_DISABLED", e); } catch
	 * (BadCredentialsException e) { throw new Exception("INVALID_CREDENTIALS", e);
	 * } }
	 */
	/**
	 * @param userDTO
	 * @return ResponseEntity
	 * @throws MethodArgumentNotValidException
	 */

	/*
	 * @PostMapping("/register") public ResponseEntity<?>
	 * register(@RequestBody @Valid UserDTO userDTO) throws
	 * MethodArgumentNotValidException { User existingUser =
	 * userService.findByUserName(userDTO.getUserName()); if (existingUser == null)
	 * { if (userDTO.getContactNo().matches("^\\d{10}$")) { User user = new User();
	 * BeanUtils.copyProperties(userDTO, user); User savedUser =
	 * userService.saveUser(user); UserDTO dto = new UserDTO();
	 * BeanUtils.copyProperties(savedUser, dto);
	 * logger.info("User Registered Successfully"); return new
	 * ResponseEntity<UserDTO>(dto, HttpStatus.CREATED); } return
	 * ResponseEntity.status(HttpStatus.BAD_REQUEST) .body(new
	 * MessageResponse("Number should only contain digits")); }
	 * 
	 * return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new
	 * MessageResponse("User Already Exists")); }
	 */
	/**
	 * @return ResponseEntity<List<UserDTO>>
	 */
	@GetMapping
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		List<UserDTO> dtos = new ArrayList<>();
		for (User user : users) {
			UserDTO dto = new UserDTO();
			BeanUtils.copyProperties(user, dto);
			dtos.add(dto);
		}
		return new ResponseEntity<List<UserDTO>>(dtos, HttpStatus.OK);
	}

	/**
	 * @param userId
	 * @return ResponseEntity
	 */
	@GetMapping("/{userid}")
	public ResponseEntity<?> getUserById(@Valid @Pattern(regexp = "[0-9]") @PathVariable int userid) {
		
		User user = userService.getUserById(userid);
		if (user != null) {
			UserDTO userDTO = new UserDTO();
			BeanUtils.copyProperties(user, userDTO);
			return new ResponseEntity<UserDTO>(userDTO, HttpStatus.CREATED);
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new MessageResponse("User Doesn`t Exist for the userId : " + String.valueOf(userid)));
	}

	/**
	 * @param userid
	 * @param userDTO
	 * @return ResponseEntity
	 */
	@PutMapping("/{userid}")
	public ResponseEntity<?> updateUser(@Valid @Pattern(regexp = "[0-9]") @PathVariable("userid") int userid,
			@RequestBody @Valid UserDTO userDTO) {
		User savedUser = userService.getUserById(userid);
		if (savedUser != null) {
			if (userDTO.getContactNo().matches("^\\d{10}$")) {
				User user = new User();
				BeanUtils.copyProperties(userDTO, user);
				savedUser = userService.updateUser(userid, user);
				UserDTO dto = new UserDTO();
				BeanUtils.copyProperties(savedUser, dto);
				return new ResponseEntity<UserDTO>(dto, HttpStatus.CREATED);
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					new MessageResponse("Number should only contain digits and please enter a valid email Address"));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new MessageResponse("User Doesn`t Exist for the userId : " + String.valueOf(userid)));
	}

	/**
	 * @param userid
	 * @return ResponseEntity
	 */
	@DeleteMapping("/{userid}")
	public ResponseEntity<?> deleteUser(@Valid @Pattern(regexp = "[0-9]") @PathVariable("userid") int userid) {
		User user = userService.deleteUserById(userid);
		if (user != null) {
			UserDTO userDTO = new UserDTO();
			BeanUtils.copyProperties(user, userDTO);
			return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new MessageResponse("User Doesn`t Exist for the userId : " + String.valueOf(userid)));

	}

}
