package com.hcl.ticketbooking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private UserDetailsService jwtUserDetailsService;
	
	@Autowired
	private RestAccessDeniedHandler restAccessDeniedHandler;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		// Use BCryptPasswordEncoder
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// We don't need CSRF for this example
		httpSecurity.csrf().disable()
				// don't authenticate this particular request
				.authorizeRequests()
				//.antMatchers( "/" ).permitAll( )
				.antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**").permitAll()
				//.antMatchers("/users/**").hasAnyRole("ADMIN") to use hasAnyRole method uncomment line 49 in USerServiceImpl.java
				.antMatchers(HttpMethod.GET,"/users/**").hasAnyAuthority("USER")
				
				.antMatchers("/users/**").hasAnyAuthority("ADMIN")
				
				.antMatchers("/user/**").permitAll()
				.antMatchers(HttpMethod.GET, "/trains/**").hasAnyAuthority("USER")
				.antMatchers("/trains/**").hasAnyAuthority("ADMIN")
				// all other requests need to be authenticated
				.anyRequest().authenticated()
				//.and().formLogin()
				.and()
				// make sure we use stateless session; session won't be used to
				// store user's state.
			//	.exceptionHandling().accessDeniedHandler(null)
				.exceptionHandling().accessDeniedHandler(restAccessDeniedHandler)
				.authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
				
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// Add a filter to validate the tokens with every request
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}


//	@Override
	protected void configure_(HttpSecurity http) throws Exception {
		http.csrf( ).disable( ).authorizeRequests()
		//.antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**", "/login/**").permitAll()
		.antMatchers("/trains/**").hasAnyAuthority("ADMIN")
		.antMatchers("/tickets/**").hasAnyAuthority("ADMIN", "USER")
		.antMatchers("/users/**").hasAnyAuthority("ADMIN")
		.antMatchers("/user/**").permitAll()
		.anyRequest().authenticated()
		.and().formLogin()
		.and()
		//.logout().invalidateHttpSession(true).clearAuthentication(true)
	//	.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
		//.and()
		//.exceptionHandling().accessDeniedPage("/unauthorized")
		.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		//.and().logout().logoutUrl("/logout")// ask for r u sure logout
		. and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))// not ask
		.clearAuthentication(true).invalidateHttpSession(true);
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
}