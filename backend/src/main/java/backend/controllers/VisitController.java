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

import backend.dto.VisitDTO;
import backend.models.Doctor;
import backend.models.Patient;
import backend.models.Report;
import backend.models.User;
import backend.models.Visit;
import backend.services.impl.DoctorService;
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
	
	private static Gson g = new Gson();
	
	@PostMapping(value = "/make-appointment", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('DERMATOLOGIST', 'PHARMACIST')")
	public ResponseEntity<String> makeAppointment(@RequestBody Visit newReservation){
		visitService.save(newReservation);
		
		if(!checkTermTaken(newReservation))
			return new ResponseEntity<String>("Patient unavailable", HttpStatus.OK);
		
		String patientsEmail = "filip.kresa@gmail.com"; //hardcoded for now, once we have user in session we will have his e-mail
		Long doctorId = newReservation.getDoctorId();
		LocalDateTime ldt = newReservation.getStart();
		
		notifyPatientViaEmail(patientsEmail, doctorId, ldt);
		
		return new ResponseEntity<String>(g.toJson(newReservation), HttpStatus.OK);
	}
	
	@PostMapping(value = "/report-visit", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('DERMATOLOGIST', 'PHARMACIST')")
	public ResponseEntity<String> saveAppointment(@RequestBody Report newReport){
		reportService.save(newReport);
		return new ResponseEntity<String>("Report saved!", HttpStatus.OK);
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
	
	@GetMapping("/td/{doctorId}")
	@PreAuthorize("hasAnyRole('DERMATOLOGIST', 'PHARMACIST')")
	public ResponseEntity<List<VisitDTO>> getDoctorsAppointmentsToDo(@PathVariable Long doctorId){
		
		List<Visit> visits = visitService.findByDoctorIdFuture(doctorId);
		
		List<VisitDTO> visitsDTO = new ArrayList<VisitDTO>();
		
		for (Visit visit : visits) {
			Long visitId = visit.getId();
			Patient visitPatient = patientService.findById(visit.getPatientId());
			Doctor visitDoctor = new Doctor(); // visit.getDoctorId();
			LocalDateTime visitStart = visit.getStart();
			LocalDateTime visitFinish = visit.getFinish();
			VisitDTO newVisitDTO = new VisitDTO(visitId, visitPatient, visitDoctor, visitStart, visitFinish);
			visitsDTO.add(newVisitDTO);
		}
		Collections.sort(visitsDTO);
		return new ResponseEntity<List<VisitDTO>>(visitsDTO,  HttpStatus.OK);
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
		}
		return true;
	}
	
	public void notifyPatientViaEmail(String patientsEmail, Long doctorId, LocalDateTime startTime) {
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
		      
		      String token = SecurityContextHolder.getContext().getAuthentication().getName();
			  User u = userService.findUserByEmail(token);
		      
			  String NAME = u.getName();
			  
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
	
}
