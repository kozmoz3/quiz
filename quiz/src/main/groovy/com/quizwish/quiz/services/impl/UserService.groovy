package com.quizwish.quiz.services.impl

import org.apache.commons.logging.Log
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.security.core.userdetails.User

import com.quizwish.quiz.models.Rol
//import com.quizwish.quiz.models.User
import com.quizwish.quiz.repositorys.RolRepository
import com.quizwish.quiz.repositorys.UserRepository

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

@Service("userService")
class UserService implements UserDetailsService{
	
	private static final Log LOGGER = LogFactory.getLog(UserService.class)
	
	@Autowired
	@Qualifier("rolRepository")
	RolRepository rolRepository;
	
	@Autowired
	@Qualifier("userRepository")
	UserRepository userRepository
	
	@Override
	public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
		LOGGER.info("METHOD : loadUserByUsername -- ");
		com.quizwish.quiz.models.User user = userRepository.findByCorreo(correo);
		List<GrantedAuthority> authorities = buildAuthorities(user);
		
		LOGGER.info("METHOD : loadUserByUsername -- buildUser "+buildUser(user, authorities));
		return buildUser(user, authorities);
	}

	private User buildUser(com.quizwish.quiz.models.User user, List<GrantedAuthority> authorities) {
		LOGGER.info("METHOD : buildUser -- ");
		LOGGER.info("METHOD : buildUser -- usuario = "+ user.getNombre() + " authorities = "+authorities +" User = ");
		return new org.springframework.security.core.userdetails.User(user.getCorreo(), user.getPassword(), user.isEnable(),
			                                                          true, true, true, authorities);
	}
	
	private List<GrantedAuthority> buildAuthorities(com.quizwish.quiz.models.User user){
		LOGGER.info("METHOD : buildAuthorities --");
		Set<GrantedAuthority> auths = new HashSet<GrantedAuthority> (); 
		Rol rol = rolRepository.findByIdrol(user.getIdrol())
		auths.add(new SimpleGrantedAuthority(rol.getDescripcion()));
		LOGGER.info("METHOD : buildAuthorities --  rol de usuario "+ rol.getDescripcion() + " auths "+ auths+ " ArrayList<GrantedAuthority> "+ new ArrayList<GrantedAuthority>(auths));
		return new ArrayList<GrantedAuthority>(auths);
	}
}
