package com.quizwish.quiz.configuration

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.RequestMapping



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true) //segurisar metodos por roles
class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	private static final Log LOGGER = LogFactory.getLog(SecurityConfiguration.class)

	@Autowired
	@Qualifier("userService")
	UserDetailsService userService

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)throws Exception {
		LOGGER.info("METHOD : configureGlobal ")
		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http)throws Exception{
		LOGGER.info("METHOD : configure ")		
		http.csrf().disable()
		http.authorizeRequests().antMatchers("/","/register","/logout").permitAll()
		http.authorizeRequests().antMatchers("/generales/**","/administrador/**","/estudiante/**","/home/**").permitAll()
		http.authorizeRequests().antMatchers("/admin/**").access("hasAnyRole('ROLE_ROOT')")
		http.authorizeRequests().antMatchers("/me/**").access("hasAnyRole('ROLE_ALUM')")
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/access-denied")
		http.authorizeRequests().and().formLogin().loginProcessingUrl("/logincheck")
		.loginPage("/login").defaultSuccessUrl("/loginsucces")
		.failureUrl("/login?error=true")
		.usernameParameter("email").passwordParameter("password")
		.and().logout().logoutUrl("/logout").logoutSuccessUrl("/")
	}
}
