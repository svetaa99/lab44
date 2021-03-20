package backend.services;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class TestService {
	
	private static Gson g = new Gson();
	
	public String sayHello() {
		return g.toJson("Hello");
	}
}
