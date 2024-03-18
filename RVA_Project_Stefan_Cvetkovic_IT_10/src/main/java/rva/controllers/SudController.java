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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rva.models.Sud;
import rva.services.SudService;

@RestController
public class SudController {

	@Autowired
	private SudService service;
	
	@GetMapping("/sud")
	// GetMapping je skracena verzija requestmappinga
	//@RequestMapping(method = RequestMethod.GET, path = "/sud")
	public List<Sud> getAllSud(){
		return service.getAll();
	}
	
	@GetMapping("/sud/id/{id}")
	public ResponseEntity<?> getSudById(@PathVariable int id){
		Optional<Sud> sud = service.findById(id);
		if(sud.isPresent()) {
			return ResponseEntity.ok(sud.get());
		}
		return ResponseEntity.status(404).body("Resource with requested ID: " + id + " does not exist!");
	}
	
	@GetMapping("/sud/naziv/{naziv}")
	public ResponseEntity<?> getSudByNaziv(@PathVariable String naziv){
		List<Sud> sudovi = service.getSudByNaziv(naziv);
		if(sudovi.isEmpty())
		{
			return ResponseEntity.status(404).body("Resource with Naziv: " + naziv + " does not exist!");
		}
		return ResponseEntity.ok(sudovi);
	}
	
	@PostMapping("/sud")
	public ResponseEntity createSudovi(@RequestBody Sud sud) {
		if(service.existById(sud.getId())) {
			return ResponseEntity.status(409).body("Resource already exists!");
		}
		Sud savedSud = service.create(sud);
		URI uri = URI.create("/sud/id/" + savedSud.getId());
		return ResponseEntity.created(uri).body(savedSud);
	}
	
	@PutMapping("/sud/id/{id}")
	public ResponseEntity<?> updateArtikl(@RequestBody Sud sud, @PathVariable int id){
			Optional<Sud> updatedSud = service.update(sud, id);
			if(updatedSud.isPresent()) {
				return ResponseEntity.ok(updatedSud.get());
			}
			return ResponseEntity.status(404).body("Resource with requested ID: " + id + " could not be updated because it does not exist!");
		}
	
	@DeleteMapping("/sud/id/{id}")
	public ResponseEntity<?> deleteSud(@PathVariable int id){
		if(service.existById(id)) {
			service.delete(id);
			return ResponseEntity.ok("Resource with ID: " + id + " has been deleted!");
		}
		return ResponseEntity.status(404).body("Resource with requested ID: " + id + " could not be deleted because it does not exist!");
	}
	
	
	
	
	
	
	
}
