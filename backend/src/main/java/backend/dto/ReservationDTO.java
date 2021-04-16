package backend.dto;

import backend.models.Medicine;
import backend.models.Patient;
import backend.models.Pharmacy;
import backend.models.Reservation;

public class ReservationDTO {
	
	private Patient patient;
	private Pharmacy pharmacy;
	private Medicine medicine;
	private int quantity;
	private double totalPrice;
	
	public ReservationDTO() {
		
	}
	
	
	public ReservationDTO(Patient patient, Pharmacy pharmacy, Medicine medicine, int quantity, double totalPrice) {
		super();
		this.patient = patient;
		this.pharmacy = pharmacy;
		this.medicine = medicine;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}


	public ReservationDTO(Reservation r) {
		this(r.getPatient(), r.getPharmacy(), r.getMedicine(), r.getQuantity(), r.getTotalPrice());
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


	public double getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
}
