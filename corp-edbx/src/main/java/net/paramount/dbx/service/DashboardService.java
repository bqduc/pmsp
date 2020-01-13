package net.paramount.dbx.service;

import net.paramount.dbx.entity.Dashboard;
import net.paramount.exceptions.ObjectNotFoundException;
import net.paramount.framework.service.GenericService;

public interface DashboardService extends GenericService<Dashboard, Long>{

  /**
   * Get one item with the provided code.
   * 
   * @param serial The dashboard serial
   * @return The dashboard
   * @throws ObjectNotFoundException If no such dashboard exists.
   */
	Dashboard getBySerial(String serial) throws ObjectNotFoundException;

  /**
   * Get one item with the provided code.
   * 
   * @param name The item type name
   * @return The item
   * @throws ObjectNotFoundException If no such dashboard exists.
   */
	Dashboard getByName(String name) throws ObjectNotFoundException;
}
