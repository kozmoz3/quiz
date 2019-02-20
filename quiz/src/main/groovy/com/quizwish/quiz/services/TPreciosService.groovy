package com.quizwish.quiz.services

import com.quizwish.quiz.entity.TPrecios

public interface TPreciosService {
	def getTPreciosAll();
	def getTPreciosById(int id);
	def saveTPreciosAll(List<TPrecios> lstTPrecios);
	def saveTPrecios(TPrecios tprecios);
	def updateTPrecios(TPrecios tprecios);
	def deleteTPrecios(int id);
	def removeTPrecios(int id);
}
