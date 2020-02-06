package net.paramount.lingual.service;

import java.util.Optional;

import net.paramount.entity.general.Country;
import net.paramount.exceptions.ObjectNotFoundException;
import net.paramount.framework.service.GenericService;

public interface CountryService extends GenericService<Country, Long> {

	/**
	 * Get one Country with the provided code.
	 * 
	 * @param code
	 *            The Country code
	 * @return The Country
	 * @throws ObjectNotFoundException
	 *             If no such Country exists.
	 */
	Optional<Country> getByCode(String code) throws ObjectNotFoundException;

	/**
	 * Get one Country with the provided name.
	 * 
	 * @param name
	 *            The Country name
	 * @return The Country
	 * @throws ObjectNotFoundException
	 *             If no such Country exists.
	 */
	Optional<Country> getByName(String name) throws ObjectNotFoundException;
}
