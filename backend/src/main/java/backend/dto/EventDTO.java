package backend.dto;

import java.time.LocalDateTime;

import backend.enums.VacationType;
import backend.models.Vacation;


public class EventDTO {

	private String name;
	
	private LocalDateTime start;
	
	private LocalDateTime end;
	
	private String color;
	
	public EventDTO() {
		
	}

	public EventDTO(VisitDTO visit) {
		this.name = "Appointment\n" + visit.getpatient().getName() + " " + visit.getpatient().getSurname();
		this.start = visit.getStart();
		this.end = visit.getFinish();
		this.color = "red";
	}
	
	public EventDTO(Vacation vacation) {
		this.name = vacation.getType().equals(VacationType.ABSENCE) ? "Vacation" : "Absence";
		this.start = vacation.getStart().atTime(0, 0);
		this.end = vacation.getFinish().atTime(0,0);
		this.color = this.name.equals("Vacation") ? "green" : "orange"; 
	}
	
	public EventDTO(String name, LocalDateTime start, LocalDateTime end, String color) {
		this.name = name;
		this.start = start;
		this.end = end;
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "EventDTO [name=" + name + ", start=" + start + ", end=" + end + "]";
	}

}
