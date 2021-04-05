package backend.models;

public class PharmacyMedicineAddRemoveObject {
	
	private Long pharmacyId;
	private Long medicineId;
	private int quantity;
	
	public PharmacyMedicineAddRemoveObject() {
		
	}

	public PharmacyMedicineAddRemoveObject(Long pharmacyId, Long medicineId, int quantity) {
		super();
		this.pharmacyId = pharmacyId;
		this.medicineId = medicineId;
		this.quantity = quantity;
	}
	
	public PharmacyMedicineAddRemoveObject(Long pharmacyId, Long medicineId) {
		super();
		this.pharmacyId = pharmacyId;
		this.medicineId = medicineId;
		this.quantity = 0;
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
