package com.quizwish.quiz.configuration

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

	@Autowired
	@Qualifier("userService")
	UserDetailsService userService

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)throws Exception {
		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http)throws Exception{
		http.authorizeRequests().antMatchers("/generales/**","/administrador/**","/estudiante/**").permitAll()
		     .anyRequest().authenticated()
		     .and()
	    .formLogin().loginPage("/login").loginProcessingUrl("/inlogin")
	    .usernameParameter("email").passwordParameter("password")
	    .defaultSuccessUrl("/loginsucces").permitAll()
	    .and()
	    .logout().logoutUrl("/logout").logoutSuccessUrl("/login")
	    .permitAll();
	}
}
