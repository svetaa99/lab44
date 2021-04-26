package backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.dto.MedicineQuantityDTO;
import backend.dto.OrderDTO;
import backend.models.Medicine;
import backend.models.Order;
import backend.models.OrderMedicines;
import backend.services.IMedicineService;
import backend.services.IOrderMedicinesService;
import backend.services.IOrderService;

@RestController
@RequestMapping(value = "orders")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderController {
	
	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private IMedicineService medicineService;
	
	@Autowired
	private IOrderMedicinesService omService;
	
	@PostMapping(value = "/create-order", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
		if (orderDTO.getOrderMedicines().size() == 0) {
			return new ResponseEntity<OrderDTO>(HttpStatus.BAD_REQUEST);
		}
		
		if (orderDTO.getDeadline() < 0) {
			return new ResponseEntity<OrderDTO>(HttpStatus.BAD_REQUEST);
		}
		
		Order o = new Order();
		o.setDeadline(orderDTO.getDeadline());
		orderService.save(o);
		
		List<MedicineQuantityDTO> medicines = orderDTO.getOrderMedicines();
		
		for (MedicineQuantityDTO mq : medicines) {
			Medicine m = medicineService.findById(mq.getMedicineId());
			OrderMedicines om = new OrderMedicines();
			om.setMedicine(m);
			om.setOrder(o);
			om.setQuantity(mq.getQuantity());
			
			omService.save(om);
		}
		
		return new ResponseEntity<OrderDTO>(orderDTO, HttpStatus.OK);
	}
	
}
