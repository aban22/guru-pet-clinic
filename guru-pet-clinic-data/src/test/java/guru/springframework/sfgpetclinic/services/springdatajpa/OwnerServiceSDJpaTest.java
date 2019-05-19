package guru.springframework.sfgpetclinic.services.springdatajpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.respositories.OwnerRepository;

@ExtendWith(MockitoExtension.class)
public class OwnerServiceSDJpaTest {

	private final static Long ID = 1L;
	private final static String LAST_NAME = "Smith";
	
	
	@Mock
	private OwnerRepository ownerRepository;
	
	@InjectMocks
	private OwnerServiceSDJpa ownerServiceSDJpa;
	
	private Owner ownerReturn;
	
	@BeforeEach
	public void setUp() {
		ownerReturn = Owner.builder().id(ID).lastName(LAST_NAME).build();
	}
	
	@Test
	public void findAll() {
		//given
		Set<Owner> ownersReturn = new HashSet<>();
		ownersReturn.add(Owner.builder().id(1L).build());
		ownersReturn.add(Owner.builder().id(2L).build());
		
		when(ownerRepository.findAll()).thenReturn(ownersReturn);
		
		//when
		Set<Owner> owners = ownerServiceSDJpa.findAll();
		
		//then
		assertNotNull(owners);
		assertEquals(2, owners.size());
	}

	@Test
	public void findById() {
		//given
		when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(ownerReturn));
		
		//when
		Owner owner = ownerServiceSDJpa.findById(ID);
		
		//then
		assertNotNull(owner);
	}
	
	@Test
	public void findByIdNotFound() {
		//given
		when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());
		
		//when
		Owner owner = ownerServiceSDJpa.findById(ID);
		
		//then
		assertNull(owner);
	}

	@Test
	public void save() {
		//given
		Owner ownerToSave = Owner.builder().id(ID).build();
		when(ownerRepository.save(any())).thenReturn(ownerToSave);
		
		//when
		Owner owner = ownerServiceSDJpa.save(ownerToSave);
		
		//then
		assertNotNull(owner);
		verify(ownerRepository).save(any());
	}

	@Test
	public void delete() {
		//when
		ownerServiceSDJpa.delete(ownerReturn);
		
		//then
		verify(ownerRepository, times(1)).delete(any());
	}

	@Test
	public void deleteById() {
		//when
		ownerServiceSDJpa.deleteById(ID);
		
		//then
		verify(ownerRepository, times(1)).deleteById(anyLong());
	}

	@Test
	public void findByLastName() {
		//given
		Owner ownerReturn = Owner.builder().id(1L).lastName(LAST_NAME).build();
		when(ownerRepository.findByLastName(any())).thenReturn(ownerReturn);
		
		//when
		Owner owner = ownerServiceSDJpa.findByLastName(LAST_NAME);
		
		//then
		assertEquals(LAST_NAME, owner.getLastName());
		verify(ownerRepository, times(1)).findByLastName(any());
	}
}
