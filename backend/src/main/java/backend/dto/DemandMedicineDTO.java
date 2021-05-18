package backend.dto;

import backend.models.DemandMedicine;

public class DemandMedicineDTO {
	
	private Long id;
	private Long pharmacyId;
	private Long medicineId;
	private int quantity;
	
	public DemandMedicineDTO() {
		
	}
	
	public DemandMedicineDTO(DemandMedicine dm) {
		this(dm.getId(), dm.getPharmacy().getId(), dm.getMedicine().getId(), dm.getQuantity());
	}
	
	public DemandMedicineDTO(Long pharmacyId, Long medicineId, int quantity) {
		super();
		this.pharmacyId = pharmacyId;
		this.medicineId = medicineId;
		this.quantity = quantity;
	}

	public DemandMedicineDTO(Long id, Long pharmacyId, Long medicineId, int quantity) {
		super();
		this.id = id;
		this.pharmacyId = pharmacyId;
		this.medicineId = medicineId;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(Long pharmacyId) {
		this.pharmacyId = pharmacyId;
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
