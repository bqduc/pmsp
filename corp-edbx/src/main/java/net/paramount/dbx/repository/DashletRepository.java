package net.paramount.dbx.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.paramount.dbx.entity.Dashlet;
import net.paramount.framework.repository.BaseRepository;

@Repository
public interface DashletRepository extends BaseRepository<Dashlet, Long>{
	@Query("SELECT entity FROM #{#entityName} entity WHERE ("
			+ " LOWER(entity.serial) like LOWER(CONCAT('%',:keyword,'%')) "
			+ ")"
	)
	Page<Dashlet> search(@Param("keyword") String keyword, Pageable pageable);

	@Query("SELECT entity FROM #{#entityName} entity WHERE ("
			+ " LOWER(entity.serial) like LOWER(CONCAT('%',:keyword,'%')) "
			+ ")"
	)
	List<Dashlet> find(@Param("keyword") String keyword);

	Dashlet findBySerial(String serial);
	Dashlet findByName(String name);
}
