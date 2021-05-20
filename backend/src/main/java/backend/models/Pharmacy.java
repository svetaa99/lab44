package backend.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Pharmacy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "address", nullable = false)
	private Long addressId;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "rating", nullable = false)
	private double rating;
	
	@Column(name = "pharmacist_price", nullable = false)
	private double pharmacistPrice;
	
//	@ManyToMany
//	@JoinTable(name = "pharmacy_medicines", joinColumns = @JoinColumn(name = "pharmacy_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "medicine_id", referencedColumnName = "id"))
//	private List<Medicine> medicines = new ArrayList<Medicine>();
	
	@OneToMany(mappedBy = "pharmacy")
	private List<PharmacyMedicines> pharmacyMedicines;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name="pharmacy_dermatologists", joinColumns = @JoinColumn(name = "pharmacy_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "dermatologist_id", referencedColumnName = "id"))
	private List<Dermatologist> dermatologists = new ArrayList<Dermatologist>();
	
	@OneToMany
	private List<Pharmacist> pharmacists = new ArrayList<Pharmacist>();
	
	@OneToMany
	private List<LabAdmin> labAdmins = new ArrayList<LabAdmin>();
	
	@OneToMany(mappedBy = "pharmacy")
	private List<Reservation> reservations;
	
	@OneToMany(mappedBy = "pharmacy")
	private List<Order> orders;
	
	@OneToMany(mappedBy = "pharmacy")
	private List<DemandMedicine> demands;
	
	@OneToMany(mappedBy = "pharmacy")
	private List<Promotion> promotions;
	
	public Pharmacy() {
		
	}

	public Pharmacy(Long id, String name, Long addressId, String description, double rating, double pharmacistPrice) {
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

	public void setRatinpharmacistPrice(double pharmacistPrice) {
		this.pharmacistPrice = pharmacistPrice;
	}

	public List<Dermatologist> getDermatologists() {
		return dermatologists;
	}

	public void setDermatologists(List<Dermatologist> dermatologists) {
		this.dermatologists = dermatologists;
	}

	public List<Pharmacist> getPharmacists() {
		return pharmacists;
	}

	public void setPharmacists(List<Pharmacist> pharmacists) {
		this.pharmacists = pharmacists;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pharmacy other = (Pharmacy) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pharmacy [id=" + id + ", name=" + name + ", addressId=" + addressId + ", description=" + description
				+ ", rating=" + rating + ", pharmacistPrice=" + pharmacistPrice + "]";
	}
	
	
	
}
