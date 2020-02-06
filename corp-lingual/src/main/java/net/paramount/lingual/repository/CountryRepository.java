/**
 * 
 */
package net.paramount.lingual.repository;

import org.springframework.stereotype.Repository;

import net.paramount.entity.general.Country;
import net.paramount.framework.repository.CodeNameBaseRepository;

/**
 * @author bqduc
 *
 */
@Repository
public interface CountryRepository extends CodeNameBaseRepository<Country, Long> {
}
