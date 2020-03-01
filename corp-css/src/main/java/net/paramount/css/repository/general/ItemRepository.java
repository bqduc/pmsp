package net.paramount.css.repository.general;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.paramount.entity.general.GeneralItem;
import net.paramount.framework.repository.BaseRepository;

@Repository
public interface ItemRepository extends BaseRepository<GeneralItem, Long>{
	@Query("SELECT entity FROM #{#entityName} entity WHERE ("
			+ " LOWER(entity.code) like LOWER(CONCAT('%',:keyword,'%')) "
			+ ")"
	)
	Page<GeneralItem> search(@Param("keyword") String keyword, Pageable pageable);

	GeneralItem findByCode(String code);
	GeneralItem findByName(String name);
}
