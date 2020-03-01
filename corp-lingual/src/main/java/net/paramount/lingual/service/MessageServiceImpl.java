package net.paramount.lingual.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.paramount.domain.entity.general.Language;
import net.paramount.exceptions.ObjectNotFoundException;
import net.paramount.framework.repository.BaseRepository;
import net.paramount.framework.service.GenericServiceImpl;
import net.paramount.lingual.entity.Label;
import net.paramount.lingual.entity.Message;
import net.paramount.lingual.repository.MessageRepository;


@Service
public class MessageServiceImpl extends GenericServiceImpl<Message, Long> implements MessageService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4336689659408835418L;

	@Inject 
	private MessageRepository repository;
	
	protected BaseRepository<Message, Long> getRepository() {
		return this.repository;
	}

	@Override
	public List<Message> getByLanguage(Language language) throws ObjectNotFoundException {
		return this.repository.findByLanguage(language);
	}

	@Override
	public Message getByLabelAndLanguage(Label label, Language language) throws ObjectNotFoundException {
		return this.repository.findByLabelAndLanguage(label, language);
	}
}