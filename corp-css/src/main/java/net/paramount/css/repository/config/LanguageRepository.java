package net.paramount.css.repository.config;

import org.springframework.stereotype.Repository;

import net.paramount.domain.entity.general.Language;
import net.paramount.framework.repository.CodeNameBaseRepository;

@Repository("languageRepository")
public interface LanguageRepository extends CodeNameBaseRepository<Language, Long>{
}
