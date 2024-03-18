package rva.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rva.models.Predmet;
import rva.models.Ucesnik;
import rva.repository.UcesnikRepository;
import rva.services.UcesnikService;

@Component
public class UcesnikServiceImpl implements UcesnikService {
	
	@Autowired
	private UcesnikRepository repo;

	@Override
	public List<Ucesnik> getAll() {
		return repo.findAll();
	}

	@Override
	public boolean existById(int id) {
		return repo.existsById(id);
	}
	
	@Override
	public Optional<Ucesnik> findById(int id)
	{
		return repo.findById(id);
	}

	@Override
	public Ucesnik create(Ucesnik t) {
		return repo.save(t);
	}

	@Override
	public Optional<Ucesnik> update(Ucesnik t, int id) {
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
	public List<Ucesnik> getUcesnikByMbr(String mbr) {
		return repo.findByMbrContainingIgnoreCase(mbr);
	}

}
