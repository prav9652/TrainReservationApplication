package com.hcl.ticketbooking.controller;

import java.util.HashSet;

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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ticketbooking.JwtTokenUtil;
import com.hcl.ticketbooking.dto.LoginDTO;
import com.hcl.ticketbooking.dto.UserDTO;
import com.hcl.ticketbooking.entity.Role;
import com.hcl.ticketbooking.entity.User;
import com.hcl.ticketbooking.response.MessageResponse;
import com.hcl.ticketbooking.service.UserService;


@RestController
@RequestMapping("/user")
public class LoginController {

	private final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * @param userName
	 * @param password
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginDTO authenticationRequest) throws Exception {

		final Authentication authentication = authenticate(authenticationRequest.getUsername(),
				authenticationRequest.getPassword());

		//final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final String token = jwtTokenUtil.generateToken(authentication);

		return ResponseEntity.ok(token);
	}

	private Authentication authenticate(String username, String password) throws Exception {
		try {
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	/**
	 * @param userDTO
	 * @return ResponseEntity
	 * @throws MethodArgumentNotValidException
	 */
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody @Valid UserDTO userDTO) throws MethodArgumentNotValidException {
		User existingUser = userService.findByUserName(userDTO.getUserName());
		if (existingUser == null) {
			if (userDTO.getContactNo().matches("^\\d{10}$")) {
				User user = new User();
				BeanUtils.copyProperties(userDTO, user);
				User savedUser = userService.saveUser(user);
				UserDTO dto = new UserDTO();
				BeanUtils.copyProperties(savedUser, dto);
				logger.info("User Registered Successfully");
				return new ResponseEntity<UserDTO>(dto, HttpStatus.CREATED);
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new MessageResponse("Number should only contain digits"));
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("User Already Exists"));
	}

	@GetMapping("/unauthorized")
	public String accessDenied() {
		return "<h1> You Don't have access</h1>";
	}
}