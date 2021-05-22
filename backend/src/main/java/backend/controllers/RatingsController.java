package backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.models.Dermatologist;
import backend.models.Doctor;
import backend.models.Ratings;
import backend.models.User;
import backend.services.impl.DoctorService;
import backend.services.impl.RatingService;
import backend.services.impl.ReservationService;
import backend.services.impl.UserService;
import backend.services.impl.VisitService;

@RestController
@RequestMapping(value = "ratings")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RatingsController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private VisitService visitService;
	
	@Autowired
	private RatingService ratingService;
	
	@Autowired
	private DoctorService doctorService;
	
	@GetMapping("/rate-pharmacy/{pharmacyId}/{mark}")
	public ResponseEntity<String> ratePharmacy(@PathVariable Long pharmacyId, @PathVariable int mark) {
		String token = SecurityContextHolder.getContext().getAuthentication().getName();
		User u = userService.findUserByEmail(token);
		
		if (!reservationService.findByPatientAndPharmacyReserved(pharmacyId, u.getId()).isEmpty() || !visitService.findByPatientAndPharmacy(u.getId(), pharmacyId).isEmpty()) {
			Ratings rating = new Ratings();
			rating.setMark(mark);
			rating.setObjId(pharmacyId);
			rating.setPatientId(u.getId());
			rating.setType(1); // 1 for pharmacy
			
			ratingService.save(rating);
			
			return new ResponseEntity<String>(HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(HttpStatus.OK);
		}
		
	}
	
	@GetMapping("/rate-medicine/{medicineId}/{mark}")
	public ResponseEntity<String> rateMedicine(@PathVariable Long medicineId, @PathVariable int mark) {
		String token = SecurityContextHolder.getContext().getAuthentication().getName();
		User u = userService.findUserByEmail(token);
		
		if (reservationService.findByPatientAndMedicineReserved(u.getId(), medicineId).isEmpty()) {
			return new ResponseEntity<String>("Nema pravo", HttpStatus.BAD_REQUEST);
		}
		
		Ratings rating = new Ratings();
		rating.setMark(mark);
		rating.setObjId(medicineId);
		rating.setPatientId(u.getId());
		rating.setType(4); // 4 for medicine
		
		ratingService.save(rating);
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@GetMapping("/rate-docotr/{doctorId}/{mark}")
	public ResponseEntity<String> rateDoctor(@PathVariable Long doctorId, @PathVariable int mark) {
		String token = SecurityContextHolder.getContext().getAuthentication().getName();
		User u = userService.findUserByEmail(token);
		
		if (visitService.findByDoctorAndPatient(doctorId, u.getId()).isEmpty()) {
			return new ResponseEntity<String>("Nema pravo", HttpStatus.BAD_REQUEST);
		}
		
		Ratings rating = new Ratings();
		rating.setMark(mark);
		rating.setObjId(doctorId);
		rating.setPatientId(u.getId());
		
		Doctor d = doctorService.findById(rating.getObjId());
		if (d instanceof Dermatologist) {
			rating.setType(3);
		} else {
			rating.setType(2);
		}
		
		ratingService.save(rating);
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@GetMapping("/get-rating/{objId}/{type}")
	public ResponseEntity<Double> getRating(@PathVariable Long objId, @PathVariable int type) {
		List<Ratings> ratings = ratingService.findByObjIdAndType(objId, type);
		int count = ratings.size();
		int total = 0;
		for (Ratings rating : ratings) {
			total += rating.getMark();
		}
		double mark = total / count;
		return new ResponseEntity<Double>(mark, HttpStatus.OK);
	}
	
}
