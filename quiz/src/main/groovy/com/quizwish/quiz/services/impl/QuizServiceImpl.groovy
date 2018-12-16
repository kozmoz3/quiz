package com.quizwish.quiz.services.impl

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.domain.Example
import org.springframework.stereotype.Service

import com.quizwish.quiz.entity.Quiz
import com.quizwish.quiz.models.User
import com.quizwish.quiz.repositorys.QuizRepository
import com.quizwish.quiz.services.QuizService

@Service("quizService")
class QuizServiceImpl implements QuizService{
	private static final Log LOGGER = LogFactory.getLog(QuizServiceImpl.class)
	
	@Autowired
	@Qualifier("quizRepository")
	def QuizRepository quizRepository
	
	@Override
	public  Quiz saveQuiz(Map<String,Object> quizMap, User user) {
		LOGGER.info("METHOD : saveQuiz ");
		Quiz quiz = new Quiz();
		quiz.setNombre(quizMap.get("nombre"))
		quiz.setDescripcion(quizMap.get("descripcion"))
		//quiz.setTipovista(String tipovista)
		
		//quiz.setImg(quizMap.get("img"))
		/*quiz.setMostrar(String mostrar)//boolean
		quiz.setVista(String vista)//boolean
		quiz.setRandom(quizMap.get("random")) 
		quiz.setTiempo(Date tiempo)
		quiz.setVenceini(Date venceini)
		quiz.setVencefin(Date vencefin) 
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
		LOGGER.info("METHOD : getQuizByIduse -- id user = ");
		return user.getQuizList();
		//return quizRepository.findAllByIduser(idUser);
	}
	
	

	@Override
	def setQuiz(Quiz quiz) {
		return quizRepository.save(quiz)
	}

	@Override
	def deleteQuiz(Integer id) {
		return quizRepository.deleteById(id)
	}


}
