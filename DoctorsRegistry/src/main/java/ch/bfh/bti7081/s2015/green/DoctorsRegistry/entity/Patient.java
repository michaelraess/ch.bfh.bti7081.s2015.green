package ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity;

public class Patient {
	
	private int id;
	private String firstname;
	private String lastname;
	private String street;
	private String zip;
	private String place;
	private String phone;
	private String mobile;
	private String email;
	private Integer height;
	private Integer weight;
	private Boolean vegetarian;
	private Boolean smoker;
	private String bloodType;
	private String allergies;
	private String intolerances;
	private String generalNotes;
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
