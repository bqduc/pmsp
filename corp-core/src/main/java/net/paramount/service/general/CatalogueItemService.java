package net.paramount.service.general;

import net.paramount.domain.entity.general.CatalogueItem;
import net.paramount.exceptions.ObjectNotFoundException;
import net.paramount.framework.service.GenericService;

public interface CatalogueItemService extends GenericService<CatalogueItem, Long> {

	/**
	 * Get one Module with the provided code.
	 * 
	 * @param code
	 *            The CatalogueItem's code
	 * @return The CatalogueItem
	 * @throws ObjectNotFoundException
	 *             If no such CatalogueItem exists.
	 */
	CatalogueItem getByCode(String code) throws ObjectNotFoundException;

	/**
	 * Get one Module with the provided code.
	 * 
	 * @param name
	 *            The CatalogueItem's name
	 * @return The CatalogueItem
	 * @throws ObjectNotFoundException
	 *             If no such CatalogueItem exists.
	 */
	CatalogueItem getByName(String name) throws ObjectNotFoundException;
}
