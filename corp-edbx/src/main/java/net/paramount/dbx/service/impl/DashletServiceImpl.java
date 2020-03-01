package net.paramount.dbx.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.paramount.dbx.entity.Dashlet;
import net.paramount.dbx.repository.DashletRepository;
import net.paramount.dbx.service.DashletService;
import net.paramount.exceptions.ObjectNotFoundException;
import net.paramount.framework.repository.BaseRepository;
import net.paramount.framework.service.GenericServiceImpl;

@Service
public class DashletServiceImpl extends GenericServiceImpl<Dashlet, Long> implements DashletService{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6623847070381520873L;

	@Inject
	private DashletRepository repository;

	@Override
	protected BaseRepository<Dashlet, Long> getRepository() {
		return this.repository;
	}

	@Override
	public Dashlet getBySerial(String serial) throws ObjectNotFoundException {
		return this.repository.findBySerial(serial);
	}

	@Override
	public Dashlet getByName(String name) throws ObjectNotFoundException {
		return this.repository.findByName(name);
	}
}
