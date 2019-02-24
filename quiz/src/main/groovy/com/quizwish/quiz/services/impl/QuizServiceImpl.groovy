package com.quizwish.quiz.services.impl

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.domain.Example
import org.springframework.stereotype.Service

import com.quizwish.quiz.entity.Quiz
import com.quizwish.quiz.entity.Quizgrupo
import com.quizwish.quiz.models.User
import com.quizwish.quiz.repositorys.QuizRepository
import com.quizwish.quiz.repositorys.QuizgrupoRepository
import com.quizwish.quiz.services.GroupService
import com.quizwish.quiz.services.QuizService
import com.quizwish.quiz.utils.CovertStringToBooleanUtil
import com.quizwish.quiz.utils.DatesUtil

@Service("quizService")
class QuizServiceImpl implements QuizService{
	private static final Log LOGGER = LogFactory.getLog(QuizServiceImpl.class)
	
	@Autowired
	@Qualifier("quizRepository")
	def QuizRepository quizRepository
	
	@Autowired
	@Qualifier("quizgrupoRepository")
	def QuizgrupoRepository quizgrupoRepository
	
	@Autowired
	@Qualifier("grupoService")
	GroupService grupoService
	
	@Override
	public  Quiz saveQuiz(Map<String,Object> quizMap, User user) {
		LOGGER.info("METHOD : saveQuiz ");
		Quiz quiz = new Quiz();
		quiz.setNombre(quizMap.get("nombre"))
		quiz.setDescripcion(quizMap.get("descripcion"))
		quiz.setImg(quizMap.get("img"))
		quiz.setPublicar(CovertStringToBooleanUtil.converStringToBoolean(quizMap.get("tipovista")))
		quiz.setShowallquestion(CovertStringToBooleanUtil.converStringToBoolean(quizMap.get("mostrarall")))
		quiz.setMostrar(CovertStringToBooleanUtil.convertBooleanToInt(quizMap.get("mostraronly"), quizMap.get("showonly")) )
		quiz.setVista(CovertStringToBooleanUtil.converStringToBoolean(quizMap.get("vistaall")))//si es true =  Todas las preguntas en una hoja And si es false= Mostrar preguntas en un wizard
		quiz.setRandom(CovertStringToBooleanUtil.converStringToBoolean(quizMap.get("random")))
		
		LOGGER.info("METHOD : saveQuiz  fecha " + quizMap.get("venceini"));
		/*quiz.setVenceini(quizMap.get("venceini"))
		quiz.setVencefin(quizMap.get("vencefin"))*/
		
		/*
		
		
		quiz.setTiempo(Date tiempo)
		
		quiz.setIntentos(String intentos)
		quiz.setPreguntasc(boolean preguntasc)
		quiz.setRespuestac(boolean respuestac)
		quiz.setPreguntasi(boolean preguntasi)
		quiz.setCalificacion(boolean calificacion)
		quiz.setGrafico(boolean grafico)
		quiz.setIstiempo(boolean istiempo)
		quiz.setMensajesop(boolean mensajesop)
		quiz.setIsintentos(boolean isintentos)
		quiz.setShowfechaini(Date showfechaini)
		quiz.setShowfechafin(Date showfechafin)
		quiz.setPassword(String password)
		quiz.setEstatus(boolean estatus)
		
		quiz.setFecha(Date fecha)
		quiz.setIduser(User iduser)*/
		quiz.setIduser(user)
		return save(quiz)
	}
	
	@Override
	def save(Quiz quiz) {
		return quizRepository.save(quiz)
	}

	@Override
	def getQuizAll() {
		return quizRepository.findAll()
	}

	@Override
	public List<Quiz>  getQuizByIduser(User user) {
		LOGGER.info("METHOD : getQuizByIduse -- id user = "+ user.nombre+" list ");
		return user.getQuizList();
		//return quizRepository.findAllByIduser(user);
	}
	
	

	@Override
	def setQuiz(Quiz quiz) {
		return quizRepository.save(quiz)
	}

	@Override
	def deleteQuiz(Integer id) {
		return quizRepository.deleteById(id)
	}

	@Override
	def getQuizById(Integer id) {
		Optional<Quiz> optional = quizRepository.findById(id)
		return optional.isPresent() ? optional.get() : new Quiz()
	}

	@Override
	public Quizgrupo relacionQuizGrupo(Integer idquiz, Integer grupo, boolean estatus) {
		Quizgrupo quizgrupo = new Quizgrupo()
		quizgrupo.setIdquiz( getQuizById( idquiz ) )
		quizgrupo.setIdgrupo( grupoService.getGroupById(grupo) )
		quizgrupo.setStatus( estatus )
		return quizgrupoRepository.save(quizgrupo)
	}


}
