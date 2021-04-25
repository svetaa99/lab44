package backend.dto;

import java.util.List;

import backend.models.Address;
import backend.models.Pharmacy;
import backend.models.Medicine;

public class PharmacyDTO {
	
	private Long id;
	private String name;
	private Long addressId;
	private String description;
	private double rating;
	private double pharmacistPrice;
	
	public PharmacyDTO(Pharmacy p) {
		this(p.getId(), p.getName(), p.getAddressId(), p.getDescription(), p.getRating(), p.getpharmacistPrice());
	}

	public PharmacyDTO(Long id, String name, Long addressId, String description, double rating, double pharmacistPrice) {
		super();
		this.id = id;
		this.name = name;
		this.addressId = addressId;
		this.description = description;
		this.rating = rating;
		this.pharmacistPrice = pharmacistPrice;
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

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
	
	public double getpharmacistPrice() {
		return pharmacistPrice;
	}

	public void setpharmacistPrice(double pharmacistPrice) {
		this.pharmacistPrice = pharmacistPrice;
	}


	@Override
	public String toString() {
		return "PharmacyDTO [id=" + id + ", name=" + name + ", addressId=" + addressId + ", description=" + description
				+ ", rating=" + rating + ", pharmacistPrice=" + pharmacistPrice + "]";
	}
	
	
}
