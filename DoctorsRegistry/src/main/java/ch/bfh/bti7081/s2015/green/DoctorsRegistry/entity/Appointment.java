package ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Appointment {
	
	private int id = -1;
	private String dateTime = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Calendar.getInstance().getTime());

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	};
	
}
