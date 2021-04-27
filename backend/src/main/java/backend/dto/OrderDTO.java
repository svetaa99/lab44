package backend.dto;

import java.util.List;

import backend.models.Order;

public class OrderDTO {
	
	private Long id;
	private List<MedicineQuantityDTO> orderMedicines;
	private long deadline;
	
	public OrderDTO(Long id, List<MedicineQuantityDTO> orderMedicines, long deadline) {
		super();
		this.id = id;
		this.orderMedicines = orderMedicines;
		this.deadline = deadline;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getDeadline() {
		return deadline;
	}

	public void setDeadline(long deadline) {
		this.deadline = deadline;
	}

	public List<MedicineQuantityDTO> getOrderMedicines() {
		return orderMedicines;
	}

	public void setOrderMedicines(List<MedicineQuantityDTO> orderMedicines) {
		this.orderMedicines = orderMedicines;
	}
	
	

}
