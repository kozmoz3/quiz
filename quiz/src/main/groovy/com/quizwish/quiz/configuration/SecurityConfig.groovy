package com.quizwish.quiz.configuration

import com.quizwish.quiz.sesion.UserDetailsServiceImpl
import javax.sql.DataSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/", "/logout").permitAll()
		http.authorizeRequests().antMatchers("/generales/**","/administrador/**","/estudiante/**").permitAll()
		http.authorizeRequests().antMatchers("/study/**").access("hasAnyRole('ROLE_ROOT', 'ROLE_ALUM')")
		http.authorizeRequests().antMatchers("/admin/**").access("hasAnyRole('ROLE_ROOT', 'ROLE_ALUM')")
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/access-denied")
		http.authorizeRequests().and().formLogin().loginProcessingUrl("/inlogin")
				.loginPage("/").defaultSuccessUrl("/admin")
				.failureUrl("/?error=true")
				.usernameParameter("email").passwordParameter("password")
				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
	}
}
