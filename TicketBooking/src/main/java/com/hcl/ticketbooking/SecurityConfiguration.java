//package com.hcl.ticketbooking;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@EnableWebSecurity
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter
//{
//
//	private static final String ADMIN = "ADMIN";
//	private static final String USER = "USER";
//
//	@Autowired
//	private UserDetailsService userDetailsService;
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService);
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {		
//		http
//        .authorizeRequests()
//        //.antMatchers("/trains/**").hasRole(ADMIN)
//		//.antMatchers("/users/**","/tickets/**").hasAnyRole(ADMIN, USER)
//        .antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**"
//        		,"/login/**")
//        .permitAll()
//        .anyRequest()//.permitAll()
//        .authenticated()
//       .and()
//       //.httpBasic()
//        .formLogin().permitAll()
//        .and()
//        .logout()
//        .invalidateHttpSession(true)
//        .clearAuthentication(true)
//        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//      //  .logoutSuccessUrl("/login?logout")
//        .permitAll().and()
//        .exceptionHandling().accessDeniedPage("/unauthorized")
//        .and()
//        .logout().logoutUrl("/logout")// ask for r u sure logout
//       // .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))// not ask
//        .clearAuthentication(true)
//        //.invalidateHttpSession(true); 
//       
//        .logoutSuccessUrl("/");
//	}
//
//	@Bean
//	public PasswordEncoder getPasswordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}
//}
