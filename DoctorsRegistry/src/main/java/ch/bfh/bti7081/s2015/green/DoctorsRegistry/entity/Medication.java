package ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity;

public class Medication {
	
	private int id = -1;
	private String name = "";
	private String descr = "";
	private int dayDose = -1;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescr() {
		return descr.substring(0, 50) + "...";
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public int getDayDose() {
		return dayDose;
	}

	public void setDayDose(int dayDose) {
		this.dayDose = dayDose;
	}
	
	@Override
	public String toString() {
		return this.name;
	}

}
