package backend.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Dermatologist extends Doctor {

	@ManyToMany(mappedBy="dermatologists")
	private List<Pharmacy> pharmacies;
	
}
