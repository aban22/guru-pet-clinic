package guru.springframework.sfgpetclinic.respositories;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.sfgpetclinic.model.PetType;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {

}
