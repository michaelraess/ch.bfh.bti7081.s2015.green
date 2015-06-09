package ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity;

import ch.bfh.bti7081.s2015.green.DoctorsRegistry.helpers.DateTimeConv;

public class Appointment {
	
	private int id = -1;
	private long dateTime = -1;
	private String descr = "";

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public long getDateTime() {
		return dateTime;
	}

	public void setDateTime(long dateTime) {
		this.dateTime = dateTime;
	}

	public String getDate() {
		return DateTimeConv.long2DateTime(this.dateTime, "yyyy-MM-dd");
	}
	
	public String getTime() {
		return DateTimeConv.long2DateTime(this.dateTime, "HH:mm:ss");
	}
	
}
