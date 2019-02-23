package com.quizwish.quiz.utils

import java.time.LocalDate

class DatesUtil {

	
   /* public static String stringToDate(String fecha){
     LocalDate date = LocalDate.parse(fecha);
     return date;
    }*/
	
	public static Date addMonths(int meses) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.MONTH, meses);
		return cal.getTime();
	}
	
}
