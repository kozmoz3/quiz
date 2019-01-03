package com.quizwish.quiz.utils

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

import java.text.SimpleDateFormat;
import java.util.Date;

class CovertStringToBooleanUtil {

	private static final Log LOGGER = LogFactory.getLog(CovertStringToBooleanUtil.class)
	
	/*public static Date StringToDate(String dateInString) {
		LOGGER.info("METHOD : StringToDate --- date = "+dateInString);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = formatter.parse(dateInString);
		return date;
		//return formatter.format(date)
	}*/
	
	public static boolean converStringToBoolean(String convert){
		LOGGER.info("METHOD : converStringToBoolean -- convert = "+convert);
		if(convert.equals("true"))
			return true;
			else
				return false;
	}
	
	public static Integer convertBooleanToInt(String convert, String numconvert) {
		LOGGER.info("METHOD : convertBooleanToInt -- convert = "+convert +" numconvert = "+numconvert);
		if(convert.equals("true")) {
			if(numconvert.equals(""))
				return new  Integer(0);
				else
			return new Integer(Integer.parseInt(numconvert));
		}else
				return new  Integer(0);
	}
				
}
