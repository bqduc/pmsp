/**
 * 
 */
package net.paramount.lingual.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.paramount.entity.general.Language;
import net.paramount.framework.repository.BaseRepository;
import net.paramount.lingual.entity.Label;
import net.paramount.lingual.entity.LabelLocalized;

/**
 * @author bqduc
 *
 */
@Repository
public interface LabelLocalizedRepository extends BaseRepository<LabelLocalized, Long> {
	List<LabelLocalized> findByLanguage(Language language);

	LabelLocalized findByLabelAndLanguage(Label label, Language language);

	@Query("SELECT entity FROM #{#entityName} entity WHERE LOWER(entity.label.name) like LOWER(:keyword) or LOWER(entity.language.code) like LOWER(:keyword) or LOWER(entity.language.name) like LOWER(:keyword)")
	List<LabelLocalized> find(@Param("keyword") String keyword);
}
