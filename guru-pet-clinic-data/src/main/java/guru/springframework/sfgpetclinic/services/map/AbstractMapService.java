package guru.springframework.sfgpetclinic.services.map;

import java.util.*;

import org.springframework.util.CollectionUtils;

import guru.springframework.sfgpetclinic.model.BaseEntity;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {

	protected Map<Long, T> map = new HashMap<>();
	
	Set<T> findAll() {
		return new HashSet<>(map.values());
	}
	
	T findById(ID id) {
		return map.get(id);
	}
	
	T save(T entity) {
		if(entity != null) {
			if(entity.getId() == null) {
				entity.setId(getNextId());
			}
			map.put(entity.getId(), entity);
		} else {
			throw new RuntimeException("Object cannot be null");
		}
		return entity;
	}
	
	void deleteById(ID id) {
		map.remove(id);
	}
	
	void delete(T entity) {
		map.entrySet().removeIf(entry -> entry.getValue().equals(entity));
	}
	
	private Long getNextId() {
		if(CollectionUtils.isEmpty(map)) {
			return 1L;
		} else {
			return Collections.max(map.keySet()) + 1;
		}
	}
}
