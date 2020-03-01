package net.paramount.auth.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.paramount.auth.entity.Authority;
import net.paramount.auth.entity.Module;
import net.paramount.auth.entity.ModuleAuthority;
import net.paramount.auth.repository.ModuleAuthorityRepository;
import net.paramount.auth.service.ModuleAuthorityService;
import net.paramount.exceptions.ObjectNotFoundException;
import net.paramount.framework.repository.BaseRepository;
import net.paramount.framework.service.GenericServiceImpl;


@Service
public class ModuleAuthorityServiceImpl extends GenericServiceImpl<ModuleAuthority, Long> implements ModuleAuthorityService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3269698913225344087L;

	@Override
	public List<ModuleAuthority> get(Module module, Authority authority) throws ObjectNotFoundException {
		return this.repository.findByModuleAndAuthority(module, authority);
	}

	@Inject 
	private ModuleAuthorityRepository repository;
	
	protected BaseRepository<ModuleAuthority, Long> getRepository() {
		return this.repository;
	}
}
