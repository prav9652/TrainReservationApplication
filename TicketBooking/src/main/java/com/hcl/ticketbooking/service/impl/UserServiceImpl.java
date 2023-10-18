package com.hcl.ticketbooking.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hcl.ticketbooking.entity.Role;
import com.hcl.ticketbooking.entity.User;
import com.hcl.ticketbooking.repository.RoleDao;
import com.hcl.ticketbooking.repository.UserRepository;
import com.hcl.ticketbooking.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private RoleDao roleDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				getAuthority(user));
	}

	private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
           // authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return authorities;
    }
	public User saveUser(User user) {
		//Role role=roleDao.findRoleByName(user.getUserRole());
		Role role = roleDao.findRoleByName("USER");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);

        if(user.getEmail().split("@")[1].contains("admin")){
            role = roleDao.findRoleByName("ADMIN");
            roleSet.add(role);
        }
        user.setRoles(roleSet);
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		return repository.save(user);
	}

	public User getUser(int playerid) {
		Optional<User> player = repository.findById(playerid);
		if (player.isPresent()) {
			return player.get();
		}
		return null;
	}

	@Override
	public User updateUser(int userid, User user) {
		User dbuser = getUser(userid);
		dbuser.setUserName(user.getUserName());
		dbuser.setPassword(user.getPassword());
		dbuser.setEmail(user.getEmail());
		dbuser.setContactNo(user.getContactNo());
		dbuser.setAge(user.getAge());
		return repository.save(dbuser);
	}

	@Override
	public User findByUserName(String username) {
		return repository.findByUserName(username);
	}

	@Override
	public List<User> getAllUsers() {
		return repository.findAll();
	}

	@Override
	public User getUserById(int userId) {
		Optional<User> user = repository.findById(userId);
		if (user.isPresent()) {
			return user.get();
		}
		return null;
	}

	@Override
	public User deleteUserById(int userid) {
		User user = getUserById(userid);
		if (user != null) {
			repository.deleteById(userid);
			return user;
		}
		return null;
	}

	@Override
	public User findByUsernameAndPassword(String userName, String password) {
		return repository.findByUserNameAndPassword(userName, password);
	}

}
