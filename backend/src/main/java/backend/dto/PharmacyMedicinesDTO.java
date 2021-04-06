package backend.dto;

import backend.models.Medicine;
import backend.models.Pharmacy;
import backend.models.PharmacyMedicines;

public class PharmacyMedicinesDTO {
	private Long id;
	private Pharmacy pharmacy;
	private Medicine medicine;
	private int quantity;
	
	public PharmacyMedicinesDTO() {
		
	}

	public PharmacyMedicinesDTO(Long id, Pharmacy pharmacy, Medicine medicine, int quantity) {
		super();
		this.id = id;
		this.pharmacy = pharmacy;
		this.medicine = medicine;
		this.quantity = quantity;
	}
	
	public PharmacyMedicinesDTO(PharmacyMedicines pm) {
		this(pm.getId(), pm.getPharmacy(), pm.getMedicine(), pm.getQuantity());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pharmacy getPharmacyId() {
		return pharmacy;
	}

	public void setPharmacyId(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	public Medicine getMedicineId() {
		return medicine;
	}

	public void setMedicineId(Medicine medicine) {
		this.medicine = medicine;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
		PharmacyMedicinesDTO other = (PharmacyMedicinesDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
