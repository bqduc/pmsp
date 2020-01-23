/**
 * 
 */
package net.paramount.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.paramount.auth.entity.Authority;
import net.paramount.auth.entity.Module;
import net.paramount.auth.entity.ModuleAuthority;
import net.paramount.framework.repository.BaseRepository;

/**
 * @author bqduc
 *
 */
@Repository
public interface ModuleAuthorityRepository extends BaseRepository<ModuleAuthority, Long> {
	@Query("SELECT entity FROM #{#entityName} entity WHERE LOWER(entity.authority.name) like LOWER(:keyword) or LOWER(entity.module.name) like LOWER(:keyword)")
	List<ModuleAuthority> find(@Param("keyword") String keyword);

	List<ModuleAuthority> findByModuleAndAuthority(Module module, Authority authority);
}
