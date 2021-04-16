package backend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="reservations")
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@ManyToOne
	@JoinColumn(name = "patient_id")
	Patient patient;
	
	@ManyToOne
	@JoinColumn(name = "pharmacy_id")
	Pharmacy pharmacy;
	
	@ManyToOne
	@JoinColumn(name = "medicine_id")
	Medicine medicine;
	
	@Column(name = "quantity")
	int quantity;
	
	@Column(name = "total_price")
	double total_price;
	
	public Reservation() {
		
	}

	public Reservation(Long id, Patient patient, Pharmacy pharmacy, Medicine medicine, int quantity, double total_price) {
		super();
		this.id = id;
		this.patient = patient;
		this.pharmacy = pharmacy;
		this.medicine = medicine;
		this.quantity = quantity;
		this.total_price = total_price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}
	
	
}
