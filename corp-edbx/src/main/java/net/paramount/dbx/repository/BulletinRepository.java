package net.paramount.dbx.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.paramount.dbx.entity.Bulletin;
import net.paramount.framework.repository.BaseRepository;

@Repository
public interface BulletinRepository extends BaseRepository<Bulletin, Long>{
	final String searchQuery = "SELECT entity FROM #{#entityName} entity WHERE ("
			+ " LOWER(entity.serial) like LOWER(CONCAT('%',:keyword,'%')) or "
			+ " LOWER(entity.title) like LOWER(CONCAT('%',:keyword,'%')) or "
			+ " LOWER(entity.contents) like LOWER(CONCAT('%',:keyword,'%')) "
			+ ")";

	@Query(searchQuery)
	Page<Bulletin> find(@Param("keyword") String keyword, Pageable pageable);

	@Query(searchQuery)
	List<Bulletin> find(@Param("keyword") String keyword);

	Bulletin findBySerial(String serial);
}
