package ch.bfh.bti7081.s2015.green.DoctorsRegistry.helpers;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeConv {
	
	public static String convertTime(long time, String fmt){
	    Date date = new Date(time);
	    Format format = new SimpleDateFormat(fmt);
	    return format.format(date);
	}

}
