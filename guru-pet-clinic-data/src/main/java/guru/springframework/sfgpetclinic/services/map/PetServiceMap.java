package guru.springframework.sfgpetclinic.services.map;

import java.util.Set;

import org.springframework.stereotype.Service;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.services.PetService;

@Service
public class PetServiceMap extends AbstractMapService<Pet, Long> implements PetService {

	@Override
	public Set<Pet> findAll() {
		return super.findAll();
	}
	
	@Override
	public Pet findById(Long id) {
		return super.findById(id);
	}
	
	@Override
	public Pet save(Pet entity) {
		super.save(entity.getId(), entity);
		return entity;
	}
	
	@Override
	public void delete(Pet entity) {
		super.delete(entity);
	}
	
	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}

}
