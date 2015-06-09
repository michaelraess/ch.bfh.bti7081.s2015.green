package ch.bfh.bti7081.s2015.green.DoctorsRegistry.helpers;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeConv {
	
	public static String long2DateTime(long time, String fmt){
	    Date date = new Date(time);
	    Format format = new SimpleDateFormat(fmt);
	    return format.format(date);
	}

	public static long dateTime2Long(String date, String time) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.parse(date + " " + time).getTime();
	}
	
}
