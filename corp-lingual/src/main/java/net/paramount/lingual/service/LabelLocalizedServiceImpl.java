package net.paramount.lingual.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.paramount.entity.general.Language;
import net.paramount.exceptions.ObjectNotFoundException;
import net.paramount.framework.repository.BaseRepository;
import net.paramount.framework.service.GenericServiceImpl;
import net.paramount.lingual.entity.Label;
import net.paramount.lingual.entity.LabelLocalized;
import net.paramount.lingual.repository.LabelLocalizedRepository;


@Service
public class LabelLocalizedServiceImpl extends GenericServiceImpl<LabelLocalized, Long> implements LabelLocalizedService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4336689659408835418L;

	@Inject 
	private LabelLocalizedRepository repository;
	
	protected BaseRepository<LabelLocalized, Long> getRepository() {
		return this.repository;
	}

	@Override
	public List<LabelLocalized> getByLanguage(Language language) throws ObjectNotFoundException {
		return this.repository.findByLanguage(language);
	}

	@Override
	public LabelLocalized getByLabelAndLanguage(Label label, Language language) throws ObjectNotFoundException {
		return this.repository.findByLabelAndLanguage(label, language);
	}
}