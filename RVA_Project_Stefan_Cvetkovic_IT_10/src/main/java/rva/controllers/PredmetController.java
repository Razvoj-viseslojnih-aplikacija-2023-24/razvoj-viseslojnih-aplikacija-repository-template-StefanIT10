package rva.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rva.models.Predmet;
import rva.models.Rociste;
import rva.models.Sud;
import rva.services.PredmetService;
import rva.services.RocisteService;
import rva.services.SudService;
import rva.services.UcesnikService;

@RestController
public class PredmetController {

	@Autowired
	private PredmetService service;
	
	@Autowired
	private SudService sudService;
	
	@GetMapping("/predmet")
	public List<Predmet> getAllPredmet(){
		return service.getAll();
	}
	
	@GetMapping("/predmet/id/{id}")
	public ResponseEntity<?> getPredmetById(@PathVariable int id){
		Optional<Predmet> predmet = service.findById(id);
		if(predmet.isPresent()) {
			return ResponseEntity.ok(predmet.get());
		}
		return ResponseEntity.status(404).body("Resource with requested ID: " + id + " does not exist!");
	}
	
	@GetMapping("/predmet/aktivan/{aktivan}")
	public ResponseEntity<?> getPredmetByAktivan(@PathVariable boolean aktivan){
		List<Predmet> predmeti = service.getPredmetByAktivan(aktivan);
		if(predmeti.isEmpty())
		{
			return ResponseEntity.status(404).body("Resource with aktivan: " + aktivan + " does not exist!");
		}
		return ResponseEntity.ok(predmeti);
	}
	
	@PostMapping("/predmet")
	public ResponseEntity createPredmet(@RequestBody Predmet predmet) {
		if(service.existById(predmet.getId())) {
			return ResponseEntity.status(409).body("Resource already exists!");
		}
		Predmet savedPredmet = service.create(predmet);
		URI uri = URI.create("/predmet/id/" + savedPredmet.getId());
		return ResponseEntity.created(uri).body(savedPredmet);
	}
	
	@PutMapping("/predmet/id/{id}")
	public ResponseEntity<?> updatePredmet(@RequestBody Predmet predmet, @PathVariable int id){
			Optional<Predmet> updatedPredmet = service.update(predmet, id);
			if(updatedPredmet.isPresent()) {
				return ResponseEntity.ok(updatedPredmet.get());
			}
			return ResponseEntity.status(404).body("Resource with requested ID: " + id + " could not be updated because it does not exist!");
		}
	
	@DeleteMapping("/predmet/id/{id}")
	public ResponseEntity<?> deletePredmet(@PathVariable int id){
		if(service.existById(id)) {
			service.delete(id);
			return ResponseEntity.ok("Resource with ID: " + id + " has been deleted!");
		}
		return ResponseEntity.status(404).body("Resource with requested ID: " + id + " could not be deleted because it does not exist!");
	}
	
	@GetMapping("/predmet/sud/{foreignKey}")
	public ResponseEntity<?> getPredmetBySud(@PathVariable int foreignKey)
	{
		Optional<Sud> sud = sudService.findById(foreignKey);
		if(sud.isPresent()) {
			List<Predmet> predmeti = service.findByForeignKey(sud.get());
					if(predmeti.isEmpty()) {
					 return ResponseEntity.status(404).body("Resource with foreign key: " + foreignKey + " does not exist!");
					}	else {
						return ResponseEntity.ok(predmeti);
					}
		}
		return ResponseEntity.status(400).body("Invalid foreign key!");
	}
	
}
