/**
 * 
 */
package net.paramount.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.paramount.auth.entity.SecurityPolicy;
import net.paramount.framework.repository.NameBaseRepository;

/**
 * @author bqduc
 *
 */
@Repository
public interface SecurityPolicyRepository extends NameBaseRepository<SecurityPolicy, Long> {

	@Query("SELECT entity FROM #{#entityName} entity WHERE LOWER(entity.name) like LOWER(:keyword) ")
	List<SecurityPolicy> find(@Param("keyword") String keyword);
}
