package backend.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

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

import backend.dto.DermatologistTermDTO;
import backend.models.Doctor;
import backend.models.DoctorTerms;
import backend.models.SearchDateTime;
import backend.models.User;
import backend.models.WorkHours;
import backend.services.impl.DoctorService;
import backend.services.impl.DoctorTermsService;
import backend.services.impl.PenaltyService;
import backend.services.impl.PharmacyService;
import backend.services.impl.UserService;
import backend.services.impl.VisitService;
import backend.services.impl.WorkHoursService;
import comparators.DoctorTermsComparator;

@RestController
@RequestMapping(value = "doctorterms")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DoctorTermsController {

	@Autowired
	private DoctorTermsService doctorTermsService;
	
	@Autowired
	private PharmacyService pharmacyService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private VisitService visitService;
	
	@Autowired
	private PenaltyService penaltyService;
	
	@Autowired
	private WorkHoursService whService;
	
	@Autowired
	private DoctorService doctorService;
	
	private static Gson g = new Gson();
	
	@GetMapping("/definedterms/{visitId}")
	@PreAuthorize("hasAnyRole('DERMATOLOGIST', 'PHARMACIST')")
	public ResponseEntity<String> getDefinedTerms(@PathVariable("visitId") Long visitId){
		String token = SecurityContextHolder.getContext().getAuthentication().getName();
		User u = userService.findUserByEmail(token);
		Long doctorId = u.getId();
		
		List<DoctorTerms> retVal = filterAndSortTerms(doctorTermsService.findByDoctorIdEquals(doctorId), visitId);
		
		return new ResponseEntity<String>(g.toJson(retVal), HttpStatus.OK);
	}
	
	@GetMapping("/definedterms-admin/{pharmacyId}/{doctorId}")
	//@PreAuthorize("hasAnyRole('PATIENT', 'LAB_ADMIN')")//commented for test purpose only
	public ResponseEntity<String> getDefinedTermsAdmin(@PathVariable("pharmacyId") Long pharmacyId, @PathVariable("doctorId") Long doctorId) {
		List<DoctorTerms> terms = doctorTermsService.findByDoctorIdEquals(doctorId);
		List<DoctorTerms> retVal = terms
				.stream()
				.filter(
					rv -> rv.getStart().isAfter(LocalDateTime.now())
					&& rv.getPharmacyId() == pharmacyId
				)
				.collect(Collectors.toList());
		
		return new ResponseEntity<String>(g.toJson(retVal), HttpStatus.OK);
	}
			
	@GetMapping("/reserve-dermatologist/{termId}")
	public ResponseEntity<List<DermatologistTermDTO>> reserveFreeTerm(@PathVariable("termId") Long termId) {
		// Get patient from token
		String token = SecurityContextHolder.getContext().getAuthentication().getName();
		User u = userService.findUserByEmail(token);
		
		// Check if has 3 penalties
		if (penaltyService.countPenaltiesByPatientId(u.getId()) >= 3) {
			return new ResponseEntity<List<DermatologistTermDTO>>(HttpStatus.BAD_REQUEST);
		}
		DoctorTerms selected = doctorTermsService.findById(termId);
		doctorTermsService.makeReservation(termId, u.getId()); // Transactional method
		// Get term from id
		
		List<DoctorTerms> appointments = doctorTermsService.findAllFutureTerms();
		List<DermatologistTermDTO> dtsDTO = new ArrayList<>();
		for (DoctorTerms doctorTerm1 : appointments) {
			User dermatologist = userService.findById(doctorTerm1.getDoctorId());
			DermatologistTermDTO dtDTO = new DermatologistTermDTO(doctorTerm1.getId(), dermatologist.getName(), dermatologist.getSurname(), pharmacyService.findById(doctorTerm1.getPharmacyId()), pharmacyService.findById(doctorTerm1.getPharmacyId()).getpharmacistPrice(), doctorTerm1.getStart());
			dtsDTO.add(dtDTO);
		}
		
		notifyPatientViaEmail(u.getEmail(), selected.getDoctorId(), selected.getStart(), u.getName());
		
		return new ResponseEntity<List<DermatologistTermDTO>>(dtsDTO, HttpStatus.OK);
	}
	
	@PostMapping(value = "/createnew/{visitId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('DERMATOLOGIST', 'PHARMACIST')") //DERMATOLOGIST ONLY
	public ResponseEntity<String> saveNewTerm(@RequestBody DoctorTerms newTerm, @PathVariable("visitId") Long visitId){

		String token = SecurityContextHolder.getContext().getAuthentication().getName();
		User u = userService.findUserByEmail(token);
		Long doctorId = u.getId();
		
		newTerm.setDoctorId(doctorId);
		
		if(checkIfTakenTerm(newTerm)) {
			if(checkIfInWorkingHours(newTerm)) {
				doctorTermsService.save(newTerm);
			}
			else {
				List<WorkHours> whs = whService.findWorkingHoursForDoctorByIdAndPharmacyId(doctorId, visitService.findById(visitId).getPharmacy());
				String ret = "Work hours|";
				for (WorkHours wh : whs) {
					ret += wh.getStartTime().toString();
					ret += "-";
					ret += wh.getFinishTime().toString();
				}
				return new ResponseEntity<String>(ret, HttpStatus.OK);
			}
		}
		else {
			return new ResponseEntity<String>("Taken term", HttpStatus.OK);
		}
		List<DoctorTerms> retVal = filterAndSortTerms(doctorTermsService.findByDoctorIdEquals(doctorId), visitId);
		
		return new ResponseEntity<String>(g.toJson(retVal), HttpStatus.OK);
	}
	
	@PostMapping(value = "/createnew-admin", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasAnyRole('LAB_ADMIN')")//commented for test purpose
	public ResponseEntity<String> saveNewTermAdmin(@RequestBody DoctorTerms newTerm) {
		String retString = doctorTermsService.createNewTerm(newTerm);
		if (!retString.equals("ok")) {
			return new ResponseEntity<String>(retString, HttpStatus.OK);
		}
		
		List<DoctorTerms> retVal = doctorTermsService
				.findByDoctorIdEquals(newTerm.getDoctorId())
				.stream()
				.filter(dt -> dt.getStart().isAfter(LocalDateTime.now()) && dt.getPharmacyId().equals(newTerm.getPharmacyId()))
				.collect(Collectors.toList());
		
		DoctorTermsComparator dtc = new DoctorTermsComparator();
		retVal.sort(dtc);
		return new ResponseEntity<String>(g.toJson(retVal), HttpStatus.OK);
	}
	
	@PostMapping(value = "/search-date-time", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE )
	@PreAuthorize("hasAnyRole('DERMATOLOGIST', 'PHARMACIST')") //DERMATOLOGIST ONLY
	public ResponseEntity<String> searchDateTime(@RequestBody SearchDateTime newDateTime){

		String token = SecurityContextHolder.getContext().getAuthentication().getName();
		User u = userService.findUserByEmail(token);
		Long doctorId = u.getId();
		
		List<DoctorTerms> retVal = doctorTermsService.findByDoctorIdEquals(doctorId);
		
		if(newDateTime.getSearchDate() != null)
			retVal = retVal.stream().filter(t -> t.getStart().toLocalDate().equals(newDateTime.getSearchDate())).collect(Collectors.toList());
		
		if(newDateTime.getSearchTime() != null)
			retVal = retVal.stream().filter(t -> t.getStart().getHour() == (newDateTime.getSearchTime().getHour())).collect(Collectors.toList());
		retVal = 
				retVal
				.stream()
				.filter(t->t.getPharmacyId() == visitService.findById(newDateTime.getVisit()).getPharmacy()
						&& t.getStart().isAfter(LocalDateTime.now()))
				.collect(Collectors.toList());
		
		return new ResponseEntity<String>(g.toJson(retVal), HttpStatus.OK);
	}
	
	@GetMapping("/dermatologist-all")
	public ResponseEntity<List<DermatologistTermDTO>> getDermatologistFreeTerms() {
		
		List<DoctorTerms> appointments = doctorTermsService.findAllFutureTerms();
		List<DermatologistTermDTO> dtsDTO = new ArrayList<>();
		for (DoctorTerms doctorTerm : appointments) {
			User dermatologist = userService.findById(doctorTerm.getDoctorId());
			DermatologistTermDTO dtDTO = new DermatologistTermDTO(doctorTerm.getId(), dermatologist.getName(), dermatologist.getSurname(), pharmacyService.findById(doctorTerm.getPharmacyId()), pharmacyService.findById(doctorTerm.getPharmacyId()).getpharmacistPrice(), doctorTerm.getStart());
			dtsDTO.add(dtDTO);
		}
		return new ResponseEntity<List<DermatologistTermDTO>>(dtsDTO, HttpStatus.OK);
	}
	
//	@GetMapping("/sort/price/{type}")
//	private ResponseEntity<List<DermatologistTermDTO>> getAllSortedByPrice(@PathVariable String type) {
//		List<DoctorTerms> dt = doctorTermsService.sortByDermatologistPrice(type);
//		return new ResponseEntity<List<DermatologistTermDTO>>(createDermatologistTermDTOList(dt), HttpStatus.OK);
//	}
	
	/*private List<DermatologistTermDTO> createDermatologistTermDTOList(List<DoctorTerms> dt) {
		List<DermatologistTermDTO> dtsDTO = new ArrayList<>();
		for (DoctorTerms doctorTerm : dt) {
			User dermatologist = userService.findById(doctorTerm.getDoctorId());
			DermatologistTermDTO dtDTO = new DermatologistTermDTO(doctorTerm.getId(), dermatologist.getName(), dermatologist.getSurname(), pharmacyService.findById(doctorTerm.getPharmacyId()), pharmacyService.findById(doctorTerm.getPharmacyId()).getpharmacistPrice(), doctorTerm.getStart());
			dtsDTO.add(dtDTO);
		}
		return dtsDTO;
	}*/
	
	private boolean checkIfTakenTerm(DoctorTerms newTerm) {
		List<DoctorTerms> doctorsTakenTerms = doctorTermsService.findByDoctorIdEquals(newTerm.getDoctorId());
		LocalDateTime startTime = newTerm.getStart();
		LocalDateTime finishTime = newTerm.getFinish();
		
		for (DoctorTerms doctorTerms : doctorsTakenTerms) {
			if(startTime.isAfter(doctorTerms.getStart()) && startTime.isBefore(doctorTerms.getFinish())) 
				return false;
			else if(finishTime.isAfter(doctorTerms.getStart()) && finishTime.isBefore(doctorTerms.getFinish())) 
				return false;
			else if(startTime.isBefore(doctorTerms.getStart()) && finishTime.isAfter(doctorTerms.getFinish())) 
				return false;
			else if(startTime.equals(doctorTerms.getStart()) || finishTime.equals(doctorTerms.getFinish()))
				return false;
		}
		return true;
	}
	private boolean checkIfInWorkingHours(DoctorTerms newTerm) {
		for (WorkHours wh : whService.findWorkingHoursForDoctorByIdAndPharmacyId(newTerm.getDoctorId(), newTerm.getPharmacyId())) {
			if(newTerm.getStart().toLocalTime().isAfter(wh.getStartTime()) && newTerm.getFinish().toLocalTime().isBefore(wh.getFinishTime()))
				return true;
		}
		return false;
	}
	private List<DoctorTerms> filterAndSortTerms(List<DoctorTerms> terms, Long visitId){
		DoctorTermsComparator dtc = new DoctorTermsComparator();
		Long pharmacyId = visitService.findById(visitId).getPharmacy();
		List<DoctorTerms> retVal = terms
		.stream()
		.filter(
				rv -> rv.getStart().isAfter(LocalDateTime.now())
				&& rv.getPharmacyId() == pharmacyId
				)
		.collect(Collectors.toList());
		retVal.sort(dtc);
		return retVal;
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
			  
			  String formatterS = "HH:mm dd-MM-yyyy";
			  DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatterS);
			  String DATE_TIME = formatter.format(startTime);
			  
		      msg.setText("Dear " + NAME + ",\nYou appointed new treatment with doctor " + DOCTORS_NAME + " at " + DATE_TIME + ".\nSincerely yours,\nLABONI44.");
		      msg.setSentDate(new Date());
		      Transport.send(msg);
		      System.out.println("Message sent.");
		    }catch (MessagingException e){ 
		    	e.printStackTrace();
		    }
	}
}
