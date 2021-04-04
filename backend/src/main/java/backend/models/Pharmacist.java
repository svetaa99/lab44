package backend.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Pharmacist extends Doctor {

	@ManyToOne
	private Pharmacy pharmacy;
	
}
