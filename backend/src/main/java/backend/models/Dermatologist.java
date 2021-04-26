package backend.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Dermatologist extends Doctor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8378058951519822223L;
	
	@ManyToMany(mappedBy="dermatologists")
	private List<Pharmacy> pharmacies;

	@Override
	public String toString() {
		return "Dermatologist [pharmacies=" + pharmacies + ", name=" + name + ", surname=" + surname + ", email="
				+ email + ", roles=" + roles + "]";
	}
	
}
