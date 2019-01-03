package com.quizwish.quiz.services.impl

import java.lang.reflect.Array
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

import com.quizwish.quiz.entity.Student
import com.quizwish.quiz.models.User
import com.quizwish.quiz.models.jmodelos.MUser
import com.quizwish.quiz.repositorys.StudentRepository
import com.quizwish.quiz.repositorys.UserRepository
import com.quizwish.quiz.services.StudentService
import com.quizwish.quiz.services.UsuarioService
import com.quizwish.quiz.utils.StatusTrueUtil


@Service("studentService")
class StudentServiceImpl implements StudentService {
	
	private static final Log LOGGER = LogFactory.getLog(StudentServiceImpl.class)
	static final def UPLOAD = ".//src//main//resources//static//estudiante//photos//"
	
	@Autowired
	@Qualifier("usuarioService")
	private UsuarioService usuarioService
	
	@Autowired
	@Qualifier("studentRepository")
	private StudentRepository studentRepository
    
	@Override
	public User findUserById(Integer idUser) {
		return usuarioService.findById(idUser);
	}
	
	@Override
	public User updateUser(User user) {
		return usuarioService.updateUser(user)
	}
	
	@Override
	public List<User>  findAllStudent(Integer idTeacher) {
		LOGGER.info("METHOD: findAllStudent")
		List<Student> studentList = StatusTrueUtil.StudentWithStatusTrue(findAllByIdTeacher(idTeacher));
		List<User> userList = new ArrayList();
		for(Student students : studentList) {
			userList.add(usuarioService.findById(students.getStudent()));
		}	
		return userList;
	}
	
	@Override
	public List<Student>  findAllByIdTeacher(Integer idTeacher) {
		LOGGER.info("METHOD: findAllByIdTeacher")
		return studentRepository.findByTeacher( idTeacher);
	}

	@Override
	public Student save(User user, User userAdmin) {
		def userExist = usuarioService.getByCorreo(user.getCorreo()) 
		LOGGER.info("METHOD: save "+userExist.toString())
		if(userExist == null) {
		return createStudent(user, userAdmin)
		}
		return createUserExist(userExist, userAdmin);
	}
	
	private Student createUserExist(User user, User userAdmin) {
		LOGGER.info("METHOD: createUserExist ")
		return saveStudent(user.getIduser(),userAdmin.getIduser())	
	}
	
	private Student createStudent(User user, User userAdmin) {
		LOGGER.info("METHOD: createStudent")
		int rol = 2;
		User userCreated = usuarioService.save(user, rol)
		return saveStudent(userCreated.getIduser(), userAdmin.getIduser())
	}
	
	private Student saveStudent(Integer idStudent, Integer idTeacher) {
		LOGGER.info("METHOD: saveStudent")
		Student student = new Student();
		student.setStudent(idStudent)
		student.setTeacher( idTeacher)
		student.setNullable(true);
		
		return studentRepository.save(student);
	}

	@Override
	public User savePersonal(User user, MUser userdata) {
		if(userdata.getTypes() != null)
			return personal( user, userdata );
		if(userdata.getCorreo() != null && !userdata.getCorreo().trim().isEmpty())
			return correo( user, userdata );
		if(userdata.getPassword() != null)
			return password( user, userdata );
		return new User()
	}
	
	private User personal(User user, MUser userdata) {
		user.setNombre(userdata.nombre)
		user.setApellidos(userdata.apellidos)
		user.setTelefono(userdata.telefono)
		user.setUsername(userdata.username)
		return usuarioService.saveSimpleStudent(user)
	}
	
	private User correo(User user, MUser userdata) {
		user.setCorreo(userdata.correo)
		return usuarioService.saveSimpleStudent(user)
	}
	
	private User password(User user, MUser userdata) {
		def encrop = new BCryptPasswordEncoder()
		user.setPassword(encrop.encode(userdata.password))
		return usuarioService.saveSimpleStudent(user)
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
		def nombre = newNameFile.replace(".", "") + "." + extension		
		user.setPerfil( nombre )
		def bitsarr = multipart.getBytes()
		def path = Paths.get( UPLOAD + nombre)
		if(isexist) {
			Files.deleteIfExists(path)
			Files.write( path, bitsarr )
		}else
			Files.write( path, bitsarr )		
		return usuarioService.saveSimpleStudent( user )
	}
	
	def getFileExtension(String fileName) {
		int dotIndex = fileName.lastIndexOf("/")
		return (dotIndex < 0) ? null : fileName.substring( dotIndex + 1 ).toLowerCase()
	}

}
