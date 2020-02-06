package net.paramount.lingual.service;

import java.util.Optional;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.paramount.exceptions.ObjectNotFoundException;
import net.paramount.framework.repository.BaseRepository;
import net.paramount.framework.service.GenericServiceImpl;
import net.paramount.lingual.entity.Label;
import net.paramount.lingual.repository.LabelRepository;


@Service
public class LabelServiceImpl extends GenericServiceImpl<Label, Long> implements LabelService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2420868378337466508L;

	@Inject 
	private LabelRepository repository;
	
	protected BaseRepository<Label, Long> getRepository() {
		return this.repository;
	}

	@Override
	public Optional<Label> getByName(String name) throws ObjectNotFoundException {
		return this.repository.findByName(name);
	}
}
