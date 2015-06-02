package ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity;

public class Appointment {
	
	private int id = -1;
	private int caseId = -1;
	private long dateTime = System.currentTimeMillis();

	public long getDateTime() {
		return dateTime;
	}

	public void setDateTime(long dateTime) {
		this.dateTime = dateTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCaseId() {
		return caseId;
	}

	public void setCaseId(int caseId) {
		this.caseId = caseId;
	};
	
}
