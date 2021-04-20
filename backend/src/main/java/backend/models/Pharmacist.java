package backend.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Pharmacist extends Doctor {

	@ManyToOne
	private Pharmacy pharmacy;

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	@Override
	public String toString() {
		return "Pharmacist [pharmacy=" + pharmacy + ", name=" + name + ", surname=" + surname + ", email=" + email
				+ ", password=" + password + ", addressId=" + addressId + ", phoneNum=" + phoneNum + "]";
	}
	
	
	
}
