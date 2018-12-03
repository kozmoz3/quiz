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

import com.quizwish.quiz.models.Rol
import com.quizwish.quiz.models.User
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
		User user = userRepository.findByCorreo(correo);
		List<GrantedAuthority> authorities = buildAuthorities(user);
	
		LOGGER.info("loadUserByUsername --> usuario "+ buildUser(user, authorities).getNombre());
		return buildUser(user, authorities);
	}

	private User buildUser(User user, List<GrantedAuthority> authorities) {
		LOGGER.info("buildUser --> usuario "+ user.getNombre());
		return new org.springframework.security.core.userdetails.User(user.getCorreo(), user.getPassword(), user.isEnable(),
			                                                          true, true, true, authorities);
	}
	
	private List<GrantedAuthority> buildAuthorities(User user){
		Set<GrantedAuthority> auths = new HashSet<GrantedAuthority> (); 
		Rol rol = rolRepository.findById(user.getIdrol())
		auths.add(new SimpleGrantedAuthority(rol.getDescripcion()));
		
		LOGGER.info("buildAuthorities -->  rol de usuario "+ rol.getDescripcion());
		return new ArrayList<GrantedAuthority>(auths);
	}
}
