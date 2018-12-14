package com.quizwish.quiz.services.impl

import org.apache.commons.logging.Log

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.domain.Example
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import com.quizwish.quiz.models.Rol
import com.quizwish.quiz.models.User
import com.quizwish.quiz.repositorys.UserRepository
import com.quizwish.quiz.services.RolService
import com.quizwish.quiz.services.UsuarioService
import org.apache.commons.logging.LogFactory
@Service("usuarioService")
class UsuarioServiceImp implements UsuarioService{
	
	private static final Log LOGGER = LogFactory.getLog(UsuarioServiceImp.class)
	
	@Autowired
	@Qualifier("rolService")
	RolService rolService
	
	@Autowired
	@Qualifier("userRepository")
	UserRepository userRepository

	@Override
	def getUsuarioAll() {
		return userRepository.findAll()
	}

	@Override
	def getUsuarioById(Integer id) {
		Optional<User> usuario = userRepository.findById(id)
		return usuario.isPresent() ? usuario.get() : new User()
	}

	@Override
	def save(User usuario, int rol) {
		LOGGER.info("METHOD: save "+usuario.toString())
		def encode = new BCryptPasswordEncoder()
		usuario.setPassword( encode.encode( usuario.getPassword() ) )
		usuario.setIdrol( rolService.getRolById(rol) )
		usuario.setEnable(true)
		return userRepository.save(usuario)
	}

	@Override
	def deleteUsuario(Integer id) {
		return userRepository.deleteById(id)
	}
	
	@Override
	def getByCorreo(String correo) {
		return userRepository.findByCorreo(correo)
	}

	@Override
	def getByCriteria(User user, int rol) {
		user.setIdrol( rolService.getRolById(rol) )
		return userRepository.findOne( Example.of( user ) )
	}

	@Override
	def getByCriteriaList(Example<User> example) {
		return userRepository.findAll(example)
	}
	
	@Override
	public User update(User userUpdate, User userSession) {
		LOGGER.info("METHOOD: save")
		LOGGER.info("METHOOD: save  --- " +getUsuarioById(userSession.getIduser()).toString())
		User user = getUsuarioById(userSession.getIduser())
		user.setNombre(userUpdate.getNombre());
		user.setApellidos(userUpdate.getApellidos())
		user.setTelefono(userUpdate.getTelefono())
		user.setCorreo(userSession.getCorreo())
		user.setUsername(userSession.getUsername())
		//user.setIdrol(userSession.getIdrol())
		user.setEnable(true);
		if(userUpdate.getPassword().equals("")) {
			user.setPassword(userSession.getPassword())
		}else {
			def encode = new BCryptPasswordEncoder()
			user.setPassword(encode.encode( userUpdate.getPassword() ));
		}
		
		return userRepository.save(user)
	}

	@Override
	public User findById(int id) {
		LOGGER.info("METHOOD: findById")
		return userRepository.getOne(id);
	}
}
