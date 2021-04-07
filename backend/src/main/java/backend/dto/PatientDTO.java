package backend.dto;

import java.time.LocalDate;

import backend.models.Patient;

public class PatientDTO {

	private Long id;
	
	private String name;
	
	private String surname;

	private Long address;

	private String category;
	
	private LocalDate date;
	


	public PatientDTO() {
		
	}
	
	public PatientDTO(Patient p, LocalDate date) {
		this(p.getId(), p.getName(), p.getSurname(), p.getAddress(), p.getCategory(), date);
	}
	
	public PatientDTO(Long id, String name, String surname, Long address, String category, LocalDate date) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.category = category;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getAddress() {
		return address;
	}

	public void setAddress(Long address) {
		this.address = address;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}	
	
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "PatientDTO [id=" + id + ", name=" + name + ", surname=" + surname + ", address=" + address
				+ ", category=" + category + "]";
	}
	
	
	
}
