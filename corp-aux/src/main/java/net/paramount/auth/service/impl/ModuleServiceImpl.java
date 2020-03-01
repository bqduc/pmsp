package net.paramount.auth.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.paramount.auth.entity.Module;
import net.paramount.auth.repository.ModuleRepository;
import net.paramount.auth.service.ModuleService;
import net.paramount.exceptions.ObjectNotFoundException;
import net.paramount.framework.repository.BaseRepository;
import net.paramount.framework.service.GenericServiceImpl;


@Service
public class ModuleServiceImpl extends GenericServiceImpl<Module, Long> implements ModuleService{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7409137083581193634L;

	@Inject 
	private ModuleRepository repository;
	
	protected BaseRepository<Module, Long> getRepository() {
		return this.repository;
	}

	@Override
	public Module getByName(String name) throws ObjectNotFoundException {
		return (Module)super.getOptionalObject(repository.findByName(name));
	}
}
