package net.paramount.auth.service;

import java.util.List;

import net.paramount.auth.entity.Authority;
import net.paramount.auth.entity.Module;
import net.paramount.auth.entity.ModuleAuthority;
import net.paramount.exceptions.ObjectNotFoundException;
import net.paramount.framework.service.GenericService;

public interface ModuleAuthorityService extends GenericService<ModuleAuthority, Long> {

	/**
	 * Get one Module with the provided code.
	 * 
	 * @param module
	 *            The Module
	 * @param authority
	 *            The Authority
	 * @return The list
	 * @throws ObjectNotFoundException
	 *             If no such Module exists.
	 */
	List<ModuleAuthority> get(Module module, Authority authority) throws ObjectNotFoundException;
}
