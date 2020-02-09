/**
 * 
 */
package net.paramount.i18n;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ducbq
 *
 */
@Repository
public interface LanguageEntityRepository extends JpaRepository<MessageSourceEntity, Integer> {
	List<MessageSourceEntity> findByLocale(String locale);

	MessageSourceEntity findByKeyAndLocale(String key, String locale);

	boolean existsByKey(String key);
}
