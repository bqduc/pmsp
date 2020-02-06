package net.paramount.lingual.service;

import java.util.Optional;

import net.paramount.entity.general.City;
import net.paramount.exceptions.ObjectNotFoundException;
import net.paramount.framework.service.GenericService;

public interface CityService extends GenericService<City, Long> {
	/**
	 * Get one City with the provided name.
	 * 
	 * @param name
	 *            The City name
	 * @return The City
	 * @throws ObjectNotFoundException
	 *             If no such City exists.
	 */
	Optional<City> getByName(String name) throws ObjectNotFoundException;
}
