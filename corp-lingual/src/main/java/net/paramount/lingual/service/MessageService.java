package net.paramount.lingual.service;

import java.util.List;

import net.paramount.domain.entity.general.Language;
import net.paramount.exceptions.ObjectNotFoundException;
import net.paramount.framework.service.GenericService;
import net.paramount.lingual.entity.Label;
import net.paramount.lingual.entity.Message;

public interface MessageService extends GenericService<Message, Long> {
	List<Message> getByLanguage(Language language) throws ObjectNotFoundException;
	Message getByLabelAndLanguage(Label label, Language language) throws ObjectNotFoundException;
}
