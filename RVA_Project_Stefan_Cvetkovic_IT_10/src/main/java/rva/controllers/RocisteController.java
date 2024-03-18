package rva.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import rva.models.Predmet;
import rva.models.Rociste;
import rva.services.PredmetService;
import rva.services.RocisteService;

@RestController
public class RocisteController {
	
	@Autowired
	private RocisteService service;
	
	@Autowired
	private PredmetService predmetService;
	
	@GetMapping("/rociste/predmet/{foreignKey}")
	public ResponseEntity<?> getRocisteByPredmet(@PathVariable int foreignKey)
	{
		Optional<Predmet> predmet = predmetService.findById(foreignKey);
		if(predmet.isPresent()) {
			List<Rociste> rocista = service.getByForeignKey(predmet.get());
					if(rocista.isEmpty()) {
					 return ResponseEntity.status(404).body("Resource with foreign key: " + foreignKey + " does not exist!");
					}	else {
						return ResponseEntity.ok(rocista);
					}
		}
		return ResponseEntity.status(400).body("Invalid foreign key!");
	}
}
