package net.paramount.dbx.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.paramount.dbx.entity.Dashboard;
import net.paramount.dbx.repository.DashboardRepository;
import net.paramount.dbx.service.DashboardService;
import net.paramount.exceptions.ObjectNotFoundException;
import net.paramount.framework.repository.BaseRepository;
import net.paramount.framework.service.GenericServiceImpl;

@Service
public class DashboardServiceImpl extends GenericServiceImpl<Dashboard, Long> implements DashboardService{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6623847070381520873L;

	@Inject
	private DashboardRepository repository;

	@Override
	protected BaseRepository<Dashboard, Long> getRepository() {
		return this.repository;
	}

	@Override
	public Dashboard getBySerial(String serial) throws ObjectNotFoundException {
		return this.repository.findBySerial(serial);
	}

	@Override
	public Dashboard getByName(String name) throws ObjectNotFoundException {
		return this.repository.findByName(name);
	}
}
