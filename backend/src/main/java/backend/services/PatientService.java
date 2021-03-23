package backend.services;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import backend.models.Patient;

public class PatientService {

	private static Gson g = new Gson();
	
	public String getAllPatients(){
		// replace with DB query
		List<Patient> retVal = new ArrayList<Patient>();
		
		// dummies
		Patient p1 = new Patient("Alan", "Ford", "Brooklyn NY", "Bronze");
		Patient p2 = new Patient("Bob", "Rock", "Brooklyn NY", "Bronze");
		Patient p3 = new Patient("Sir", "Oliver", "London", "Gold");
		
		retVal.add(p1);
		retVal.add(p2);
		retVal.add(p3);
		
		return g.toJson(retVal);
	}
	
}
