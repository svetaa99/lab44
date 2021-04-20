package backend.dto;

import backend.models.Pharmacist;
import backend.models.Pharmacy;

public class PharmacistDTO {

	private Long id;
	private String name;
	private String surname;
	private Long address;
	private Pharmacy pharmacy;
	
	public PharmacistDTO(Pharmacist p) {
		this(p.getId(), p.getName(), p.getSurname(), p.getAddress(), p.getPharmacy());
	}

	public PharmacistDTO(Long id, String name, String surname, Long address, Pharmacy pharmacy) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.pharmacy = pharmacy;
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

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	@Override
	public String toString() {
		return "PharmacistDTO [id=" + id + ", name=" + name + ", surname=" + surname + ", address=" + address
				+ ", pharmacy=" + pharmacy + "]";
	}
	
}
