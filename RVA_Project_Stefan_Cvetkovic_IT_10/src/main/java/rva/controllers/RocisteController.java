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
import rva.models.Ucesnik;
import rva.services.PredmetService;
import rva.services.RocisteService;
import rva.services.UcesnikService;

@RestController
public class RocisteController {
	
	@Autowired
	private RocisteService service;
	
	@Autowired
	private PredmetService predmetService;
	
	@Autowired
	private UcesnikService ucesnikService;
	
	@GetMapping("/rociste")
	public List<Rociste> getAllRociste(){
		return service.getAll();
	}
	
	@GetMapping("/rociste/id/{id}")
	public ResponseEntity<?> getRocisteById(@PathVariable int id){
		Optional<Rociste> rociste = service.findById(id);
		if(rociste.isPresent()) {
			return ResponseEntity.ok(rociste.get());
		}
		return ResponseEntity.status(404).body("Resource with requested ID: " + id + " does not exist!");
	}
	
	@GetMapping("/rociste/sudnica/{sudnica}")
	public ResponseEntity<?> getRocisteBySudnica(@PathVariable String sudnica){
		List<Rociste> rocista = service.getRocistaBySudnica(sudnica);
		if(rocista.isEmpty())
		{
			return ResponseEntity.status(404).body("Resource with sudnica: " + sudnica + " does not exist!");
		}
		return ResponseEntity.ok(rocista);
	}
	
	@PostMapping("/rociste")
	public ResponseEntity createRociste(@RequestBody Rociste rociste) {
		if(service.existById(rociste.getId())) {
			return ResponseEntity.status(409).body("Resource already exists!");
		}
		Rociste savedRociste = service.create(rociste);
		URI uri = URI.create("/rociste/id/" + savedRociste.getId());
		return ResponseEntity.created(uri).body(savedRociste);
	}
	
	@PutMapping("/rociste/id/{id}")
	public ResponseEntity<?> updateRociste(@RequestBody Rociste rociste, @PathVariable int id){
			Optional<Rociste> updatedRociste = service.update(rociste, id);
			if(updatedRociste.isPresent()) {
				return ResponseEntity.ok(updatedRociste.get());
			}
			return ResponseEntity.status(404).body("Resource with requested ID: " + id + " could not be updated because it does not exist!");
		}
	
	@DeleteMapping("/rociste/id/{id}")
	public ResponseEntity<?> deleteRociste(@PathVariable int id){
		if(service.existById(id)) {
			service.delete(id);
			return ResponseEntity.ok("Resource with ID: " + id + " has been deleted!");
		}
		return ResponseEntity.status(404).body("Resource with requested ID: " + id + " could not be deleted because it does not exist!");
	}
	
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
	
	@GetMapping("/rociste/ucesnik/{foreignKey}")
	public ResponseEntity<?> getRocisteByUcesnik(@PathVariable int foreignKey)
	{
		Optional<Ucesnik> ucesnik = ucesnikService.findById(foreignKey);
		if(ucesnik.isPresent()) {
			List<Rociste> rocista = service.getByForeignKey(ucesnik.get());
					if(rocista.isEmpty()) {
					 return ResponseEntity.status(404).body("Resource with foreign key: " + foreignKey + " does not exist!");
					}	else {
						return ResponseEntity.ok(rocista);
					}
		}
		return ResponseEntity.status(400).body("Invalid foreign key!");
	}
}
