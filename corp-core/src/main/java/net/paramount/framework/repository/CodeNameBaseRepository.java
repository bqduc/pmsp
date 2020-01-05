/**
 * 
 */
package net.paramount.framework.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author bqduc
 *
 */
@NoRepositoryBean
public interface CodeNameBaseRepository<T, PK extends Serializable> extends BaseRepository<T, PK> {
	Optional<T> findByCode(String code);
	Optional<T> findByName(String name);

	Long countByCode(String code);
	Long countByName(String name);
}
