package com.quizwish.quiz.services.impl

import org.apache.commons.logging.Log

import java.lang.reflect.Array
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.domain.Example
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

import com.quizwish.quiz.models.Rol
import com.quizwish.quiz.models.User
import com.quizwish.quiz.repositorys.UserRepository
import com.quizwish.quiz.services.RolService
import com.quizwish.quiz.services.UsuarioService
import org.apache.commons.logging.LogFactory
@Service("usuarioService")
class UsuarioServiceImp implements UsuarioService{
	
	private static final Log LOGGER = LogFactory.getLog(UsuarioServiceImp.class)
	static final def UPLOAD = ".//src//main//resources//static//administrador//photos//"
	
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
		User user = findById(userSession.getIduser())
		user.setNombre(userUpdate.getNombre());
		user.setApellidos(userUpdate.getApellidos())
		user.setTelefono(userUpdate.getTelefono())
		user.setCorreo(userSession.getCorreo())
		user.setUsername(userSession.getUsername())
		//user.setIdrol(userSession.getIdrol())
		user.setEnable(true);
		user.setPassword(encodePassword(userUpdate.getPassword(), userSession.getPassword()))
		
		return userRepository.save(user)
	}
	
	@Override
	public User updateUser(User user) {
		LOGGER.info("METHOD: updateUser "+user.toString())
		User userUpdate = findById(user.getIduser())
		userUpdate.setNombre(user.getNombre());
		userUpdate.setApellidos(user.getApellidos())
		userUpdate.setTelefono(user.getTelefono())
		LOGGER.info("METHOD: updateUser  -- correo "+userUpdate.getCorreo())
		 String correo =userUpdate.getCorreo()
	    userUpdate.setCorreo(correo)
		userUpdate.setUsername(user.getUsername())
		//user.setIdrol(userSession.getIdrol())
		userUpdate.setEnable(true);
		userUpdate.setPassword(encodePassword(user.getPassword(), userUpdate.getPassword()))
		LOGGER.info("METHOD: updateUser  -- user "+userUpdate.toString())
		return userRepository.save(userUpdate)
	} 
	
	private String encodePassword(String password, String passworEncode) {
		LOGGER.info("METHOD: encodePassword "+password +" passwordEncode "+passworEncode)
		if(password.equals("")) {
			return passworEncode
		}else {
			def encode = new BCryptPasswordEncoder()
			return encode.encode( password );
		}
	}

	@Override
	public User findById(Integer id) {
		LOGGER.info("METHOOD: findById")
		return userRepository.getOne(id);
	}

	@Override
	def saveSimpleStudent(User user) {
		return userRepository.save(user)
	}

	@Override
	public User saveProfile(User user, MultipartFile multipart) {
		LOGGER.info("METHOD - saveProfile");
		def newNameFile = ""
		def isexist = false
		def extension = getFileExtension(multipart.getContentType())
		if (extension == null ) return user
		
		if (user.getPerfil() != null ) {
			newNameFile = user.getPerfil().replace("." + extension, "")
			isexist = true
		}else {
			def encrop = new BCryptPasswordEncoder()
			def fechaencript = new Date();
			newNameFile = encrop.encode(multipart.getOriginalFilename() +""+ fechaencript.getTime())
		}
		LOGGER.info("METHOD - saveProfile archivo => " + newNameFile);
		return sendArchiveAndSave(user, multipart, newNameFile, extension, isexist)
	}
	
	private User sendArchiveAndSave(User user, MultipartFile multipart, String newNameFile, String extension, boolean isexist) {
		LOGGER.info("METHOD - sendArchiveAndSave => Nombre arch: "+newNameFile + " Extensión: "+extension +" Exist: "+ isexist);
		def nombre = newNameFile.replace(".", "") + "." + extension
		user.setPerfil( nombre )
		def bitsarr = multipart.getBytes()
		def path = Paths.get( UPLOAD + nombre)
		if(isexist) {
			Files.deleteIfExists(path)
			Files.write( path, bitsarr )
		}else
			Files.write( path, bitsarr )
		return userRepository.save(user)
	}
	
	def getFileExtension(String fileName) {
		int dotIndex = fileName.lastIndexOf("/")
		return (dotIndex < 0) ? null : fileName.substring( dotIndex + 1 ).toLowerCase()
	}
}
