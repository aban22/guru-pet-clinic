package guru.springframework.sfgpetclinic.services.map;

import java.util.Set;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.services.CrudService;

public class PetServiceMap extends AbstractMapService<Pet, Long> implements CrudService<Pet, Long> {

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
