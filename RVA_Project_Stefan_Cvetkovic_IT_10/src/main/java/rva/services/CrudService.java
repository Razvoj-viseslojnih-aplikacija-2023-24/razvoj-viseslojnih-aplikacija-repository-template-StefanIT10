package rva.services;

import java.util.List;
import java.util.Optional;

public interface CrudService<T> {
	
	List<T> getAll();
	
	boolean existById(int id);
	
	T create(T t);
	
	Optional<T> update(T t, int id);
	
	void delete(int id);
	
	Optional<T> findById(int id);

}
