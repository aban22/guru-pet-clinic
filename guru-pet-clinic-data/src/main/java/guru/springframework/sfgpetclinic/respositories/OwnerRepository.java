package guru.springframework.sfgpetclinic.respositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.sfgpetclinic.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

	Owner findByLastName(String lastName);
	
	List<Owner> findByLastNameContainingIgnoreCase(String lastName);
}
