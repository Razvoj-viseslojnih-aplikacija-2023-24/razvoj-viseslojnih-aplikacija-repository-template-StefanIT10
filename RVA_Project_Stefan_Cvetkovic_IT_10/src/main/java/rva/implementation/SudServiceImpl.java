package rva.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rva.models.Predmet;
import rva.models.Sud;
import rva.repository.SudRepository;
import rva.services.SudService;

@Component
public class SudServiceImpl implements SudService {
	
	@Autowired
	private SudRepository repo;

	@Override
	public List<Sud> getAll() {
		return repo.findAll();
	}

	@Override
	public boolean existById(int id) {
		return repo.existsById(id);
	}
	
	@Override
	public Optional<Sud> findById(int id)
	{
		return repo.findById(id);
	}

	@Override
	public Sud create(Sud t) {
		return repo.save(t);
	}

	@Override
	public Optional<Sud> update(Sud t, int id) {
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
	public List<Sud> getSudByNaziv(String naziv) {
		return repo.findByNazivContainingIgnoreCase(naziv);
	}

}
