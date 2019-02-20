package com.quizwish.quiz.services.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

import com.quizwish.quiz.entity.Contrato
import com.quizwish.quiz.repositorys.ContratoRepository
import com.quizwish.quiz.services.ContratoService

@Service("contratoService")
class ContratoServiceImp implements ContratoService{
	@Autowired
	@Qualifier("contratoRepository")
	ContratoRepository contratoRepository

	@Override
	public List<Contrato> getContratoAll() {
		return contratoRepository.findAll()
	}

	@Override
	public Contrato getContratoById(String folio) {
		return contratoRepository.findById(folio)
	}

	@Override
	def saveContratoAll(List<Contrato> lstcontrato) {
		return contratoRepository.saveAll(lstcontrato);
	}

	@Override
	public Object saveContrato(Contrato contrato) {
		return contratoRepository.save(contrato);
	}

	@Override
	public Object updateContrato(Contrato contrato) {
		return contratoRepository.save(contrato);
	}

	@Override
	public Object deleteContrato(String folio) {
		return contratoRepository.deleteById(folio);
	}

	@Override
	public Object removeContrato(String folio) {
		return null;
	}
}
