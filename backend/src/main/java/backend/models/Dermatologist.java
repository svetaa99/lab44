package backend.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Dermatologist extends Doctor {

	@ManyToMany(mappedBy="dermatologists")
	private List<Pharmacy> pharmacies;

	@Override
	public String toString() {
		return "Dermatologist [name=" + name + ", surname=" + surname + ", email=" + email + ", password=" + password
				+ ", addressId=" + addressId + ", phoneNum=" + phoneNum + "]";
	}
	
	
}
