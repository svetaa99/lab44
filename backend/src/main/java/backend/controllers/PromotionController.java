package backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.services.IPromotionService;

@RestController
@RequestMapping(value = "promotions")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PromotionController {
	
	@Autowired
	private IPromotionService promoService;
	
	
}
