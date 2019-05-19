package guru.springframework.sfgpetclinic.services.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.springframework.sfgpetclinic.model.Owner;

public class OwnerServiceMapTest {

	private static final Long OWNER_ID = 1L;
	private static final String LAST_NAME = "Smith";
	
	private OwnerServiceMap ownerServiceMap;
	
	@BeforeEach
	public void setUp() {
		ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());

		ownerServiceMap.save(Owner.builder().id(OWNER_ID).lastName(LAST_NAME).build());
	}
	
	@Test
	public void findAll() {
		Set<Owner> owners = ownerServiceMap.findAll();
		
		assertEquals(1, owners.size());
	}
	
	@Test
	public void findById() {
		Owner owner = ownerServiceMap.findById(OWNER_ID);
		
		assertEquals(OWNER_ID, owner.getId());
	}
	
	@Test
	public void saveExistId() {
		Long id = 2L;
		Owner owner = Owner.builder().id(id).build();
		Owner ownerSaved = ownerServiceMap.save(owner);
		
		assertEquals(id, ownerSaved.getId());
	}
	
	@Test
	public void saveNoId() {
		
		Owner ownerSaved = ownerServiceMap.save(Owner.builder().build());
		
		assertNotNull(ownerSaved);
		assertNotNull(ownerSaved.getId());
	}
	
	@Test
	public void deleteById() {
		ownerServiceMap.deleteById(OWNER_ID);
		assertEquals(0, ownerServiceMap.findAll().size());
	}

	@Test
	public void delete() {
		ownerServiceMap.delete(ownerServiceMap.findById(OWNER_ID));
		assertEquals(0, ownerServiceMap.findAll().size());
	}

	@Test
	public void findByLastName() {
		Owner smith = ownerServiceMap.findByLastName(LAST_NAME);
		
		assertNotNull(smith);
		assertEquals(OWNER_ID, smith.getId());
	}
	
	@Test
	public void findByLastNameNotFound() {
		Owner smith = ownerServiceMap.findByLastName("foo");
		
		assertNull(smith);
	}
}
