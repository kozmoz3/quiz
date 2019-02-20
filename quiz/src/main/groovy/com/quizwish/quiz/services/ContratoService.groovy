package com.quizwish.quiz.services

import com.quizwish.quiz.entity.Contrato

public interface ContratoService {
	def getContratoAll();
	def getContratoById(String folio);
	def saveContratoAll(List<Contrato> lstcontrato);
	def saveContrato(Contrato contrato);
	def updateContrato(Contrato contrato);
	def deleteContrato(String folio);
	def removeContrato(String folio);
}
