package rva.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rva.models.Predmet;
import rva.models.Rociste;
import rva.models.Ucesnik;
import rva.repository.RocisteRepository;
import rva.services.RocisteService;

@Component
public class RocisteServiceImpl implements RocisteService {
	
	@Autowired
	private RocisteRepository repo;

	@Override
	public List<Rociste> getAll() {
		return repo.findAll();
	}

	@Override
	public boolean existById(int id) {
		return repo.existsById(id);
	}

	@Override
	public Rociste create(Rociste t) {
		return repo.save(t);
	}

	@Override
	public Optional<Rociste> update(Rociste t, int id) {
		if(existById(id)) {
			t.setId(id);
		return Optional.of(repo.save(t));
	}
		return Optional.empty();
	}

	@Override
	public void delete(int id) {
		repo.deleteById(id);

	}

	@Override
	public List<Rociste> getRocistaBySudnica(String sudnica) {
		return repo.findBySudnicaContainingIgnoreCase(sudnica);
	}

	@Override
	public List<Rociste> getByForeignKey(Ucesnik ucesnik) {
		return repo.findByUcesnik(ucesnik);
	}

	@Override
	public List<Rociste> getByForeignKey(Predmet predmet) {
		return repo.findByPredmet(predmet);
	}

}
