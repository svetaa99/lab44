package backend.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import backend.dto.PatientDTO;
import backend.dto.ReservationDTO;
import backend.dto.VisitDTO;
import backend.enums.Status;
import backend.models.Doctor;
import backend.models.Patient;
import backend.models.Pharmacist;
import backend.models.Report;
import backend.models.User;
import backend.models.Visit;
import backend.models.WorkHours;
import backend.services.impl.DoctorService;
import backend.services.impl.DoctorTermsService;
import backend.services.impl.PatientService;
import backend.services.impl.ReportService;
import backend.services.impl.UserService;
import backend.services.impl.VisitService;

@RestController
@RequestMapping(value = "appointments")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class VisitController {

	@Autowired
	private VisitService visitService;
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private ReportService reportService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private DoctorTermsService dtService;
	
	private static Gson g = new Gson();
	
	@PostMapping(value = "/make-appointment", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('DERMATOLOGIST', 'PHARMACIST')")
	public ResponseEntity<String> makeAppointment(@RequestBody Visit newReservation){
		String token = SecurityContextHolder.getContext().getAuthentication().getName();
		User u = userService.findUserByEmail(token);
		Long doctorId = u.getId();
		
		newReservation.setDoctorId(doctorId);
		newReservation.setStatus(Status.RESERVED);
		
		if(!checkTermTaken(newReservation))
			return new ResponseEntity<String>("Patient unavailable", HttpStatus.OK);
		
		if(u instanceof Pharmacist) {
			System.out.println("\n\nJESTE FARMACEUUUT\n\n");
			if(!checkIfInWorkingHours(newReservation)) {
				System.out.println("\n\nJESTE FARMACEUUUT\n\n");
				return new ResponseEntity<String>("Not in your working hours", HttpStatus.OK);
			}
		}
		
		visitService.save(newReservation);
		
		Patient p = patientService.findById(newReservation.getPatientId());
		String patientsEmail = p.getEmail();
		
		LocalDateTime ldt = newReservation.getStart();
		
		notifyPatientViaEmail(patientsEmail, doctorId, ldt, p.getName());
		
		return new ResponseEntity<String>(g.toJson(newReservation), HttpStatus.OK);
	}

	@PostMapping(value = "/report-visit", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('DERMATOLOGIST', 'PHARMACIST')")
	public ResponseEntity<String> saveAppointment(@RequestBody Report newReport){
		Visit v = visitService.findById(newReport.getVisitId());
		v.setStatus(Status.FINISHED);
		System.out.println("Int: " + Status.FINISHED);
		visitService.save(v);
		
		reportService.save(newReport);
		return new ResponseEntity<String>("Report saved!", HttpStatus.OK);
	}
	
	@GetMapping("/to-dermatologists")
	public ResponseEntity<List<VisitDTO>> getMyAppointmentsToDermatologists() {
		String token = SecurityContextHolder.getContext().getAuthentication().getName();
		User u = userService.findUserByEmail(token);
		
		List<Visit> appointments = visitService.findByPatientIdEquals(u.getId());
		List<Visit> myAppointments = new ArrayList<Visit>();
		
		for (Visit appointment : appointments) {
			if (appointment.getStatus().equals(Status.RESERVED)) {
				myAppointments.add(appointment);
			}
		}
		
		List<VisitDTO> visitsDTO = new ArrayList<>();
		for (Visit visit : myAppointments) {
			VisitDTO visitDTO = new VisitDTO(visit.getId(), patientService.findById(visit.getPatientId()), doctorService.findById(visit.getDoctorId()), visit.getStart(), visit.getFinish());
			visitsDTO.add(visitDTO);
		}
		
		return new ResponseEntity<List<VisitDTO>>(visitsDTO, HttpStatus.OK);
	}
	
	@GetMapping("/cancel-my-reservation-to-dermatologists/{id}")
	public ResponseEntity<VisitDTO> cancelAppointment(@PathVariable Long id) {
		Visit visit = visitService.findById(id);
		
		visit.setStatus(Status.CANCELED);
		
		visitService.save(visit);
		
		return new ResponseEntity<VisitDTO>(HttpStatus.OK);
	}

	@GetMapping("/doctor/{doctorId}")
	@PreAuthorize("hasAnyRole('DERMATOLOGIST', 'PHARMACIST')")
	public ResponseEntity<String> getAppointmentsForDoctor(@PathVariable Long doctorId){
		List<Visit> appointments = visitService.findByDoctorIdEquals(doctorId);
		return new ResponseEntity<String>(g.toJson(appointments), HttpStatus.OK);
	}
	
	@GetMapping("/patient/{patientId}")
	@PreAuthorize("hasAnyRole('DERMATOLOGIST', 'PHARMACIST')")
	public ResponseEntity<String> getAppointmentsForPatient(@PathVariable Long patientId){
		List<Visit> appointments = visitService.findByPatientIdEquals(patientId);
		return new ResponseEntity<String>(g.toJson(appointments), HttpStatus.OK);
	}
	
	@GetMapping("/td")
	@PreAuthorize("hasAnyRole('DERMATOLOGIST', 'PHARMACIST')")
	public ResponseEntity<List<VisitDTO>> getDoctorsAppointmentsToDo(){
		String token = SecurityContextHolder.getContext().getAuthentication().getName();
		Long docId = userService.findUserByEmail(token).getId();
		
		List<Visit> visits = visitService.findByDoctorIdFuture(docId);
		
		List<VisitDTO> visitsDTO = new ArrayList<VisitDTO>();
		
		for (Visit visit : visits) {
			Long visitId = visit.getId();
			Patient visitPatient = patientService.findById(visit.getPatientId());
			Doctor visitDoctor = doctorService.findById(visit.getDoctorId());
			LocalDateTime visitStart = visit.getStart();
			LocalDateTime visitFinish = visit.getFinish();
			VisitDTO newVisitDTO = new VisitDTO(visitId, visitPatient, visitDoctor, visitStart, visitFinish);
			
			if(visit.getStatus() == Status.RESERVED)
				visitsDTO.add(newVisitDTO);
		}
		Collections.sort(visitsDTO);
		return new ResponseEntity<List<VisitDTO>>(visitsDTO,  HttpStatus.OK);
	}
	@GetMapping("/get-user/{visitId}")
	@PreAuthorize("hasAnyRole('DERMATOLOGIST', 'PHARMACIST')")
	public ResponseEntity<PatientDTO> getPatientByVisitId(@PathVariable Long visitId){
		Visit v = visitService.findById(visitId);
		Patient p = patientService.findById(v.getPatientId());
		
		return new ResponseEntity<PatientDTO>(new PatientDTO(p), HttpStatus.OK);
	}
	
	@GetMapping("/get-pharmacy/{visitId}")
	@PreAuthorize("hasAnyRole('DERMATOLOGIST', 'PHARMACIST')")
	public ResponseEntity<Long> getPharmacyIdByVisitId(@PathVariable Long visitId){
		Visit v = visitService.findById(visitId);
		Long pharmacyId = v.getPharmacy();
		return new ResponseEntity<Long>(pharmacyId, HttpStatus.OK);
	}
	
	private boolean checkTermTaken(Visit newReservation) {
		List<Visit> patientsAppointments = visitService.findByPatientIdEquals(newReservation.getPatientId());
		
		LocalDateTime startTime = newReservation.getStart();
		LocalDateTime finishTime = newReservation.getFinish();

		for (Visit visit : patientsAppointments) {
			if(startTime.isAfter(visit.getStart()) && startTime.isBefore(visit.getFinish())) 
				return false;
			else if(finishTime.isAfter(visit.getStart()) && finishTime.isBefore(visit.getFinish())) 
				return false;
			else if(startTime.isBefore(visit.getStart()) && finishTime.isAfter(visit.getFinish()))
				return false;
			else if(startTime.equals(visit.getStart()) || finishTime.equals(visit.getFinish()))
				return false;
		}
		//check for doctor as well
		return true;
	}
	
	public void notifyPatientViaEmail(String patientsEmail, Long doctorId, LocalDateTime startTime, String patientsName) {
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		 // Get a Properties object
		    Properties props = System.getProperties();
		    props.setProperty("mail.smtp.host", "smtp.gmail.com");
		    props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		    props.setProperty("mail.smtp.socketFactory.fallback", "false");
		    props.setProperty("mail.smtp.port", "465");
		    props.setProperty("mail.smtp.socketFactory.port", "465");
		    props.put("mail.smtp.auth", "true");
		    props.put("mail.debug", "true");
		    props.put("mail.store.protocol", "pop3");
		    props.put("mail.transport.protocol", "smtp");
		    final String username = "labonibato44@gmail.com";//
		    final String password = "filipsvetauki1";
		    try{
		      Session session = Session.getDefaultInstance(props, 
		                          new Authenticator(){
		                             protected PasswordAuthentication getPasswordAuthentication() {
		                                return new PasswordAuthentication(username, password);
		                             }});

		   // -- Create a new message --
		      Message msg = new MimeMessage(session);

		   // -- Set the FROM and TO fields --
		      msg.setFrom(new InternetAddress("labonibato44@gmail.com"));
		      msg.setRecipients(Message.RecipientType.TO, 
		                        InternetAddress.parse(patientsEmail, false));
		      msg.setSubject("Doctors appointment");
		      
			  String NAME = patientsName;
			  
			  Doctor doc = doctorService.findById(doctorId);
			  String DOCTORS_NAME = doc.getName();
			  
			  String DATE_TIME = startTime.toString();
			  
		      msg.setText("Dear " + NAME + ",\nYour doctor " + DOCTORS_NAME + " has appointed new treatment on " + DATE_TIME + ".\nSincerely yours,\nLABONI44.");
		      msg.setSentDate(new Date());
		      Transport.send(msg);
		      System.out.println("Message sent.");
		    }catch (MessagingException e){ 
		    	e.printStackTrace();
		    }
	}
	
	private boolean checkIfInWorkingHours(Visit newTerm) {
		for (WorkHours wh : dtService.findWorkingHoursForDoctorByIdAndPharmacyId(newTerm.getDoctorId(), newTerm.getPharmacy())) {
			if(newTerm.getStart().toLocalTime().isAfter(wh.getStartTime()) && newTerm.getFinish().toLocalTime().isBefore(wh.getFinishTime()))
				return true;
		}			
		return false;
	}
	
}
