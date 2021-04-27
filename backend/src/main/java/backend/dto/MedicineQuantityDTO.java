package backend.dto;

import java.util.List;

import backend.models.Medicine;

public class MedicineQuantityDTO {
	
	private Long medicineId;
	private int quantity;
	
	public MedicineQuantityDTO() {
		
	}
	
	public MedicineQuantityDTO(Long medicineId, int quantity) {
		this.medicineId = medicineId;
		this.quantity = quantity;
	}

	public Long getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(Long medicineId) {
		this.medicineId = medicineId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
