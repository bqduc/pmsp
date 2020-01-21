package net.paramount.auth.service;

import net.paramount.auth.entity.Module;
import net.paramount.exceptions.ObjectNotFoundException;
import net.paramount.framework.service.GenericService;

public interface ModuleService extends GenericService<Module, Long> {

	/**
	 * Get one Module with the provided code.
	 * 
	 * @param name
	 *            The Module name
	 * @return The Module
	 * @throws ObjectNotFoundException
	 *             If no such Module exists.
	 */
	Module getByName(String name) throws ObjectNotFoundException;
}
