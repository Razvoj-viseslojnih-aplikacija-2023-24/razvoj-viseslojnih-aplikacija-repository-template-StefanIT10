package rva.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rva.models.Predmet;
import rva.models.Sud;
import rva.repository.PredmetRepository;
import rva.services.PredmetService;

@Component
public class PredmetServiceImpl implements PredmetService {
	
	@Autowired
	private PredmetRepository repo;

	@Override
	public List<Predmet> getAll() {
		return repo.findAll();
	}

	@Override
	public boolean existById(int id) {
		return repo.existsById(id);
	}
	
	@Override
	public Optional<Predmet> findById(int id)
	{
		return repo.findById(id);
	}

	@Override
	public Predmet create(Predmet t) {
		return repo.save(t);
	}

	@Override
	public Optional<Predmet> update(Predmet t, int id) {
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
	public List<Predmet> getPredmetByAktivan(boolean aktivan) {
		return repo.findByAktivanEquals(aktivan);
	}

	@Override
	public List<Predmet> findByForeignKey(Sud sud) {
		return repo.findBySud(sud);
	}

}
