package guru.springframework.sfgpetclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.respositories.PetTypeRepository;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Profile("springdatajpa")
public class PetTypeServiceSDJpa implements PetTypeService {

	private final PetTypeRepository petTypeRepository;
	
	@Override
	public Set<PetType> findAll() {
		Set<PetType> types = new HashSet<>();
		petTypeRepository.findAll().forEach(types::add);
		return types;
	}

	@Override
	public PetType findById(Long id) {
		return petTypeRepository.findById(id).orElse(null);
	}

	@Override
	public PetType save(PetType entity) {
		return petTypeRepository.save(entity);
	}

	@Override
	public void delete(PetType entity) {
		petTypeRepository.delete(entity);
	}

	@Override
	public void deleteById(Long id) {
		petTypeRepository.deleteById(id);
	}

}
