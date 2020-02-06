package net.paramount.lingual.service;

import java.util.Optional;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.paramount.entity.general.Country;
import net.paramount.exceptions.ObjectNotFoundException;
import net.paramount.framework.repository.BaseRepository;
import net.paramount.framework.service.GenericServiceImpl;
import net.paramount.lingual.repository.CountryRepository;


@Service
public class CountryServiceImpl extends GenericServiceImpl<Country, Long> implements CountryService {
	private static final long serialVersionUID = 1567818094326905142L;
	
	@Inject 
	private CountryRepository repository;
	
	protected BaseRepository<Country, Long> getRepository() {
		return this.repository;
	}

	@Override
	public Optional<Country> getByName(String name) throws ObjectNotFoundException {
		return this.repository.findByName(name);
	}

	@Override
	public Optional<Country> getByCode(String code) throws ObjectNotFoundException {
		return this.repository.findByCode(code);
	}
}
