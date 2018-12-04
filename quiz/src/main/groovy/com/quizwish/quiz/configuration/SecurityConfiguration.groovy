package com.quizwish.quiz.configuration

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder



@Configuration
@EnableWebSecurity
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
		http.authorizeRequests().antMatchers("/generales/**","/administrador/**","/estudiante/**").permitAll()
		     .anyRequest().authenticated()
		     .and()
	    .formLogin().loginPage("/login").loginProcessingUrl("/logincheck")
	    .usernameParameter("email").passwordParameter("password")
	    .defaultSuccessUrl("/loginsucces").permitAll()
	    .and()
	    .logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout")
	    .permitAll();
	}
}