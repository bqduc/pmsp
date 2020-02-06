/**
 * 
 */
package net.paramount.lingual.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import net.paramount.entity.general.City;
import net.paramount.framework.repository.BaseRepository;

/**
 * @author bqduc
 *
 */
@Repository
public interface CityRepository extends BaseRepository<City, Long> {
	Optional<City> findByName(String name);
}
