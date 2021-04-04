package backend.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PharmacyMedicines {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@ManyToOne
	@JoinColumn(name = "pharmacy_id")
	Pharmacy pharmacy;
	
	@ManyToOne
	@JoinColumn(name = "medicine_id")
	Medicine medicine;
	
	@Column(name = "quantity", columnDefinition = "Int default '0'")
	int quantity;
	
	public PharmacyMedicines() {
		
	}

	public PharmacyMedicines(Long id, Pharmacy pharmacy, Medicine medicine, int quantity) {
		super();
		this.id = id;
		this.pharmacy = pharmacy;
		this.medicine = medicine;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	
}
