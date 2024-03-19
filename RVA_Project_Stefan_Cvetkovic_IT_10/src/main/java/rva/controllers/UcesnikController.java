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

import rva.models.Sud;
import rva.models.Ucesnik;
import rva.services.SudService;
import rva.services.UcesnikService;

@RestController
public class UcesnikController {
	
	@Autowired
	private UcesnikService service;
	
	@GetMapping("/ucesnik")
	public List<Ucesnik> getAllUcesnik(){
		return service.getAll();
	}
	
	@GetMapping("/ucesnik/id/{id}")
	public ResponseEntity<?> getUcesnikById(@PathVariable int id){
		Optional<Ucesnik> ucesnik = service.findById(id);
		if(ucesnik.isPresent()) {
			return ResponseEntity.ok(ucesnik.get());
		}
		return ResponseEntity.status(404).body("Resource with requested ID: " + id + " does not exist!");
	}
	
	@GetMapping("/ucesnik/mbr/{mbr}")
	public ResponseEntity<?> getUcesnikByMbr(@PathVariable String mbr){
		List<Ucesnik> ucesnici = service.getUcesnikByMbr(mbr);
		if(ucesnici.isEmpty())
		{
			return ResponseEntity.status(404).body("Resource with MBR: " + mbr + " does not exist!");
		}
		return ResponseEntity.ok(ucesnici);
	}
	
	@PostMapping("/ucesnik")
	public ResponseEntity createUcesnik(@RequestBody Ucesnik ucesnik) {
		if(service.existById(ucesnik.getId())) {
			return ResponseEntity.status(409).body("Resource already exists!");
		}
		Ucesnik savedUcesnik = service.create(ucesnik);
		URI uri = URI.create("/ucesnik/id/" + savedUcesnik.getId());
		return ResponseEntity.created(uri).body(savedUcesnik);
	}
	
	@PutMapping("/ucesnik/id/{id}")
	public ResponseEntity<?> updateUcesnik(@RequestBody Ucesnik ucesnik, @PathVariable int id){
			Optional<Ucesnik> updatedUcesnik = service.update(ucesnik, id);
			if(updatedUcesnik.isPresent()) {
				return ResponseEntity.ok(updatedUcesnik.get());
			}
			return ResponseEntity.status(404).body("Resource with requested ID: " + id + " could not be updated because it does not exist!");
		}
	
	@DeleteMapping("/ucesnik/id/{id}")
	public ResponseEntity<?> deleteUcesnik(@PathVariable int id){
		if(service.existById(id)) {
			service.delete(id);
			return ResponseEntity.ok("Resource with ID: " + id + " has been deleted!");
		}
		return ResponseEntity.status(404).body("Resource with requested ID: " + id + " could not be deleted because it does not exist!");
	}
	

}
