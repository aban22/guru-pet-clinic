package guru.springframework.sfgpetclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.respositories.VisitRepository;
import guru.springframework.sfgpetclinic.services.VisitService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Profile("springdatajpa")
public class VisitServiceSDJpa implements VisitService {

	private final VisitRepository visitRepository;
	
	@Override
	public Set<Visit> findAll() {
		Set<Visit> visits = new HashSet<>();
		visitRepository.findAll().forEach(visits::add);
		return visits;
	}

	@Override
	public Visit findById(Long id) {
		return visitRepository.findById(id).orElse(null);
	}

	@Override
	public Visit save(Visit entity) {
		return visitRepository.save(entity);
	}

	@Override
	public void delete(Visit entity) {
		visitRepository.delete(entity);
	}

	@Override
	public void deleteById(Long id) {
		visitRepository.deleteById(id);
	}

}
