package net.paramount.dbx.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.paramount.dbx.entity.Dashboard;
import net.paramount.framework.repository.BaseRepository;

@Repository
public interface DashboardRepository extends BaseRepository<Dashboard, Long>{
	@Query("SELECT entity FROM #{#entityName} entity WHERE ("
			+ " LOWER(entity.serial) like LOWER(CONCAT('%',:keyword,'%')) "
			+ ")"
	)
	Page<Dashboard> search(@Param("keyword") String keyword, Pageable pageable);

	@Query("SELECT entity FROM #{#entityName} entity WHERE ("
			+ " LOWER(entity.serial) like LOWER(CONCAT('%',:keyword,'%')) "
			+ ")"
	)
	List<Dashboard> find(@Param("keyword") String keyword);

	Dashboard findBySerial(String serial);
	Dashboard findByName(String name);
}
