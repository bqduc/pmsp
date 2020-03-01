package net.paramount.service.general;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.paramount.domain.entity.general.CatalogueItem;
import net.paramount.exceptions.ObjectNotFoundException;
import net.paramount.framework.repository.BaseRepository;
import net.paramount.framework.service.GenericServiceImpl;
import net.paramount.repository.CatalogueItemRepository;


@Service
public class CatalogueItemServiceImpl extends GenericServiceImpl<CatalogueItem, Long> implements CatalogueItemService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4566594774661053895L;

	@Inject 
	private CatalogueItemRepository repository;
	
	protected BaseRepository<CatalogueItem, Long> getRepository() {
		return this.repository;
	}

	@Override
	public CatalogueItem getByCode(String code) throws ObjectNotFoundException {
		return super.getOptional(this.repository.findByCode(code));
	}

	@Override
	public CatalogueItem getByName(String name) throws ObjectNotFoundException {
		return super.getOptional(this.repository.findByName(name));
	}
}
