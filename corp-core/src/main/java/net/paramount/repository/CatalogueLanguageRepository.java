/**
 * 
 */
package net.paramount.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.paramount.domain.entity.general.CatalogueItem;
import net.paramount.domain.entity.general.CatalogueLanguage;
import net.paramount.domain.entity.general.Language;
import net.paramount.framework.repository.BaseRepository;

/**
 * @author bqduc
 *
 */
@Repository
public interface CatalogueLanguageRepository extends BaseRepository<CatalogueLanguage, Long> {
	List<CatalogueLanguage> findByLanguage(Language language);

	CatalogueLanguage findByCatalogueAndLanguage(CatalogueItem catalogue, Language language);

	@Query("SELECT entity FROM #{#entityName} entity WHERE LOWER(entity.catalogue.code) like LOWER(:keyword) or LOWER(entity.catalogue.name) like LOWER(:keyword) ")
	List<CatalogueLanguage> find(@Param("keyword") String keyword);
}
