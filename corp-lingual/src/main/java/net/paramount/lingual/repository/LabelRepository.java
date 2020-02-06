/**
 * 
 */
package net.paramount.lingual.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.paramount.framework.repository.BaseRepository;
import net.paramount.lingual.entity.Label;

/**
 * @author bqduc
 *
 */
@Repository
public interface LabelRepository extends BaseRepository<Label, Long> {
	Optional<Label> findByName(String name);

	Long countByName(String name);

	@Query("SELECT entity FROM #{#entityName} entity WHERE LOWER(entity.name) like LOWER(:keyword) ")
	List<Label> find(@Param("keyword") String keyword);
}
