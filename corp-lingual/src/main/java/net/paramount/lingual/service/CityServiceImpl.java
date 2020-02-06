package net.paramount.lingual.service;

import java.util.Optional;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.paramount.entity.general.City;
import net.paramount.exceptions.ObjectNotFoundException;
import net.paramount.framework.repository.BaseRepository;
import net.paramount.framework.service.GenericServiceImpl;
import net.paramount.lingual.repository.CityRepository;

@Service
public class CityServiceImpl extends GenericServiceImpl<City, Long> implements CityService {
	private static final long serialVersionUID = 2833195615724439778L;

	@Inject 
	private CityRepository repository;
	
	protected BaseRepository<City, Long> getRepository() {
		return this.repository;
	}

	@Override
	public Optional<City> getByName(String name) throws ObjectNotFoundException {
		return this.repository.findByName(name);
	}
}
