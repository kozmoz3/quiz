package com.quizwish.quiz.services.impl

import com.quizwish.quiz.entity.TPrecios
import com.quizwish.quiz.repositorys.TPreciosRepository
import com.quizwish.quiz.services.TPreciosService

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service("tpreciosService")
class TPreciosServiceImp implements TPreciosService{
	@Autowired
	@Qualifier("tpreciosRepository")
	TPreciosRepository tpreciosRepository

	@Override
	public List<TPrecios> getTPreciosAll() {
		return tpreciosRepository.findAll();
	}

	@Override
	public TPrecios getTPreciosById(int id) {
		Optional<TPrecios> opcional = tpreciosRepository.findById(id);
		return opcional.isPresent() ? opcional.get() : new TPrecios();
	}

	@Override
	def saveTPreciosAll(List<TPrecios> lstTPrecios) {
		return tpreciosRepository.saveAll(lstTPrecios);
	}

	@Override
	def saveTPrecios(TPrecios tprecios) {
		return tpreciosRepository.save(tprecios);
	}

	@Override
	def updateTPrecios(TPrecios tprecios) {
		return tpreciosRepository.save(tprecios);
	}

	@Override
	def deleteTPrecios(int id) {
		return tpreciosRepository.deleteById(id);
	}

	@Override
	def removeTPrecios(int id) {
		return null;
	}
}
