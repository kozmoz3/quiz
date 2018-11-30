package com.quizwish.quiz.utils

class ToExampleQuery <T>{
	T objClassing
	Object[] args
	
	ToExampleQuery(T typeclass, Object... values){
		objClassing = typeclass
		args = values
	}
	
	def toExample() {
		return objClassing.getClass().newInstance(args)
	}
}
