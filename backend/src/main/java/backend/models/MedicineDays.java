package backend.models;

public class MedicineDays {

	private Medicine medicine;
	
	private int days;
	
	public MedicineDays() {
		
	}

	public MedicineDays(Medicine medicine, int days) {
		super();
		this.medicine = medicine;
		this.days = days;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	@Override
	public String toString() {
		return "MedicineDays [medicine=" + medicine + ", days=" + days + "]";
	}
	
}
