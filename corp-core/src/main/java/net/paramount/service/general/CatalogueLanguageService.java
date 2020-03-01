package net.paramount.service.general;

import java.util.List;

import net.paramount.domain.entity.general.CatalogueItem;
import net.paramount.domain.entity.general.CatalogueLanguage;
import net.paramount.domain.entity.general.Language;
import net.paramount.exceptions.ObjectNotFoundException;
import net.paramount.framework.service.GenericService;

public interface CatalogueLanguageService extends GenericService<CatalogueLanguage, Long> {
	List<CatalogueLanguage> getByLanguage(Language language) throws ObjectNotFoundException;
	CatalogueLanguage getByCatalogueAndLanguage(CatalogueItem catalogue, Language language) throws ObjectNotFoundException;
}
