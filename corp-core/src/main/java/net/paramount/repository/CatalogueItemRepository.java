/**
 * 
 */
package net.paramount.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.paramount.domain.entity.general.CatalogueItem;
import net.paramount.framework.repository.CodeNameBaseRepository;

/**
 * @author bqduc
 *
 */
@Repository
public interface CatalogueItemRepository extends CodeNameBaseRepository<CatalogueItem, Long> {

	@Query("SELECT entity FROM #{#entityName} entity WHERE LOWER(entity.code) like LOWER(:keyword) or LOWER(entity.name) like LOWER(:keyword) ")
	List<CatalogueItem> find(@Param("keyword") String keyword);
}
