package ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity;

import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.annotations.neoEntity;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.annotations.neoId;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.annotations.neoProperty;

@neoEntity
public class Patient {
	
	@neoId
	private int id;
	@neoProperty
	private String firstname;
	@neoProperty
	private String lastname;
	@neoProperty
	private String street;
	@neoProperty
	private String zip;
	@neoProperty
	private String place;
	@neoProperty
	private String phone;
	@neoProperty
	private String mobile;
	@neoProperty
	private String email;
	@neoProperty
	private Integer height;
	@neoProperty
	private Integer weight;
	@neoProperty
	private Boolean vegetarian;
	@neoProperty
	private Boolean smoker;
	@neoProperty
	private String bloodType;
	@neoProperty
	private String allergies;
	@neoProperty
	private String intolerances;
	@neoProperty
	private String generalNotes;
	@neoProperty
	private String biography;
	
	public Patient() {
		this.firstname = "";
		this.lastname = "";
		this.street = "";
		this.zip = "";
		this.place = "";
		this.phone = "";
		this.mobile = "";
		this.email = "";
		this.height = 0;
		this.weight = 0;
		this.vegetarian = false;
		this.smoker = false;
		this.bloodType = "";
		this.allergies = "";
		this.intolerances = "";
		this.generalNotes = "";
		this.biography = "";
	}
	
	@Override
	public String toString() {
		return this.firstname + " " + this.lastname;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public Boolean getVegetarian() {
		return vegetarian;
	}
	public void setVegetarian(Boolean vegetarian) {
		this.vegetarian = vegetarian;
	}
	public Boolean getSmoker() {
		return smoker;
	}
	public void setSmoker(Boolean smoker) {
		this.smoker = smoker;
	}
	public String getBloodType() {
		return bloodType;
	}
	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}
	public String getAllergies() {
		return allergies;
	}
	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}
	public String getIntolerances() {
		return intolerances;
	}
	public void setIntolerances(String intolerances) {
		this.intolerances = intolerances;
	}
	public String getGeneralNotes() {
		return generalNotes;
	}
	public void setGeneralNotes(String generalNotes) {
		this.generalNotes = generalNotes;
	}
	public String getBiography() {
		return biography;
	}
	public void setBiography(String biography) {
		this.biography = biography;
	}
	
}
