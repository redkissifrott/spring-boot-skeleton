package com.nnk.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	// @Autowired
	// private DataSource dataSource;

	// @Bean
	// public UserDetailsService userDetailsService() {
	// return new CustomUserDetailsServices();
	// }

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// @Bean
	// public DaoAuthenticationProvider authenticationProvider() {
	// DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	// authProvider.setUserDetailsService(userDetailsService());
	// authProvider.setPasswordEncoder(passwordEncoder());
	//
	// return authProvider;
	// }

	// @Override
	// protected void configure(AuthenticationManagerBuilder auth)
	// throws Exception {
	// auth.authenticationProvider(authenticationProvider());
	// }

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	// @Override
	// protected void configure(HttpSecurity http) throws Exception {
	// http.csrf().disable().authorizeRequests().antMatchers("/users")
	// .hasRole("USER").anyRequest().authenticated().and().formLogin()
	// .usernameParameter("username")
	// .defaultSuccessUrl("/bidList/list", true).permitAll()
	//
	// .and().logout().logoutSuccessUrl("/").permitAll();
	// }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().formLogin()
				.defaultSuccessUrl("/bidList/list", true).permitAll().and()
				.logout().and().authorizeRequests().antMatchers("/users")
				.hasRole("USER").anyRequest().authenticated();
	}

}