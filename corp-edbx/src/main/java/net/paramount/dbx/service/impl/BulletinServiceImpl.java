package net.paramount.dbx.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.paramount.dbx.entity.Bulletin;
import net.paramount.dbx.repository.BulletinRepository;
import net.paramount.dbx.service.BulletinService;
import net.paramount.exceptions.ObjectNotFoundException;
import net.paramount.framework.repository.BaseRepository;
import net.paramount.framework.service.GenericServiceImpl;

@Service
public class BulletinServiceImpl extends GenericServiceImpl<Bulletin, Long> implements BulletinService{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8504155567942413469L;

	@Inject
	private BulletinRepository repository;

	@Override
	protected BaseRepository<Bulletin, Long> getRepository() {
		return this.repository;
	}

	@Override
	public Bulletin getBySerial(String serial) throws ObjectNotFoundException {
		return this.repository.findBySerial(serial);
	}
}
