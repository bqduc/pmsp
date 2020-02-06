package net.paramount.lingual.service;

import java.util.List;

import net.paramount.entity.general.Language;
import net.paramount.exceptions.ObjectNotFoundException;
import net.paramount.framework.service.GenericService;
import net.paramount.lingual.entity.Label;
import net.paramount.lingual.entity.LabelLocalized;

public interface LabelLocalizedService extends GenericService<LabelLocalized, Long> {
	List<LabelLocalized> getByLanguage(Language language) throws ObjectNotFoundException;
	LabelLocalized getByLabelAndLanguage(Label label, Language language) throws ObjectNotFoundException;
}
