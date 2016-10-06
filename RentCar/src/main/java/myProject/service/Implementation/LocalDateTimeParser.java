package myProject.service.Implementation;

import java.time.LocalDateTime;

public class LocalDateTimeParser {
	public static LocalDateTime localDateTimeParser(String stringLocalDateTime){
    	String[] spliter = stringLocalDateTime.split(" ");
    	String[] spliterDate = spliter[0].split("-");
    	Integer year = Integer.parseInt(spliterDate[0]);
    	Integer month = Integer.parseInt(spliterDate[1]);
    	Integer dayOfMonth = Integer.parseInt(spliterDate[2]);
    	String[] spliterTime = spliter[1].split(":");
    	Integer hour = Integer.parseInt(spliterTime[0]);
    	Integer minute = Integer.parseInt(spliterTime[1]);
    	LocalDateTime dateTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
    	return dateTime;
    }
	
	public static String localDateTimeParser(LocalDateTime localDateTime){
		String year = String.valueOf(localDateTime.getYear());
		String month = String.valueOf(localDateTime.getMonthValue());
		String dayOfMonth = String.valueOf(localDateTime.getDayOfMonth());
		String hour = String.valueOf(localDateTime.getHour());
		String minute = String.valueOf(localDateTime.getMinute());
		return year +"-" +month +"-" +dayOfMonth +" " +hour +":" +minute;
	}
}
