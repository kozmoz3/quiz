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
	public Object getTPreciosAll() {
		return tpreciosRepository.findAll();
	}

	@Override
	public Object getTPreciosById(int id) {
		return tpreciosRepository.findById(id);
	}

	@Override
	public Object saveTPreciosAll(List<TPrecios> lstTPrecios) {
		return tpreciosRepository.saveAll(lstTPrecios);
	}

	@Override
	public Object saveTPrecios(TPrecios tprecios) {
		return tpreciosRepository.save(tprecios);
	}

	@Override
	public Object updateTPrecios(TPrecios tprecios) {
		return tpreciosRepository.save(tprecios);
	}

	@Override
	public Object deleteTPrecios(int id) {
		return tpreciosRepository.deleteById(id);
	}

	@Override
	public Object removeTPrecios(int id) {
		return null;
	}
}
