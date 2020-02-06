package net.paramount.lingual.service;

import java.util.Optional;

import net.paramount.exceptions.ObjectNotFoundException;
import net.paramount.framework.service.GenericService;
import net.paramount.lingual.entity.Label;

public interface LabelService extends GenericService<Label, Long> {

	/**
	 * Get one Label with the provided name.
	 * 
	 * @param name
	 *            The Label name
	 * @return The Label
	 * @throws ObjectNotFoundException
	 *             If no such Label exists.
	 */
	Optional<Label> getByName(String name) throws ObjectNotFoundException;
}
