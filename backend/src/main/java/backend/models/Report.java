package backend.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Report {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="visit_id", unique=false, nullable=false)
	private Long visitId;
	
	private List<MedicineDays> medicineDays;
	
	@Column(name="information", unique=false, nullable=true)
	private String information;
	
	public Report() {
		
	}

	public Report(Long visitId, List<MedicineDays> medicineDays, String information) {
		super();
		this.visitId = visitId;
		this.medicineDays = medicineDays;
		this.information = information;
	}
	
	public Report(Long id, Long visitId, List<MedicineDays> medicineDays, String information) {
		super();
		this.id = id;
		this.visitId = visitId;
		this.medicineDays = medicineDays;
		this.information = information;
	}

	public Long getVisitId() {
		return visitId;
	}

	public void setVisitId(Long visitId) {
		this.visitId = visitId;
	}

	public List<MedicineDays> getMedicineDays() {
		return medicineDays;
	}

	public void setMedicineDays(List<MedicineDays> medicineDays) {
		this.medicineDays = medicineDays;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	@Override
	public String toString() {
		return "Report [medicineDays=" + medicineDays + ", information=" + information + "]";
	}
	
}
