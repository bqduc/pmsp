package net.paramount.dbx.service;

import net.paramount.dbx.entity.Dashlet;
import net.paramount.exceptions.ObjectNotFoundException;
import net.paramount.framework.service.GenericService;

public interface DashletService extends GenericService<Dashlet, Long>{

  /**
   * Get one item with the provided code.
   * 
   * @param serial The item code
   * @return The item
   * @throws ObjectNotFoundException If no such account exists.
   */
	Dashlet getBySerial(String serial) throws ObjectNotFoundException;

  /**
   * Get one item with the provided code.
   * 
   * @param name The item type name
   * @return The item
   * @throws ObjectNotFoundException If no such account exists.
   */
	Dashlet getByName(String name) throws ObjectNotFoundException;
}
