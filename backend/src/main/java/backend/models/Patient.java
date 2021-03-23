package backend.models;

public class Patient {
	
	private String name;
	
	private String surname;
	
	private String address;
	
	private String category;
	
	public Patient() {
		
	}
	
	public Patient(String name, String surname, String address, String category) {
		super();
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	

}
