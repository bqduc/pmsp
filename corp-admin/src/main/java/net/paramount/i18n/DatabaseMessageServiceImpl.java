/**
 * 
 */
package net.paramount.i18n;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

import net.paramount.common.ListUtility;

/**
 * @author ducbq
 *
 */
//@ManagedBean(eager = true, name = "dbMessageSource")
@Component("dbMessageSource")
public class DatabaseMessageServiceImpl implements PersistenceMessageService/*MessageSource */{
	@Inject
	private LanguageEntityRepository messageSourceRepository;

	@Override
	public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
		return resolveMessage(code, args, locale);
	}

	@Override
	public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
		return resolveMessage(code, args, locale);
	}

	@Override
	public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
		String message = null;
		for (String code : resolvable.getCodes()) {
			message = resolveMessage(code, resolvable.getArguments(), locale);
			if (message != null) {
				return message;
			}
		}
		return null;
	}

	private String resolveMessage(String code, Object[] args, Locale locale) {
		MessageSourceEntity messageSourceEntity = messageSourceRepository.findByKeyAndLocale(code, getProcessingLocaleString(locale));
		return (messageSourceEntity==null)?code:messageSourceEntity.getContent(); 
	}

	@Override
	public void saveMessage(String key, String content, Locale locale) {
		MessageSourceEntity messageEntity = this.messageSourceRepository.findByKeyAndLocale(key, getProcessingLocaleString(locale));
		if (null==messageEntity) {
			messageEntity = MessageSourceEntity.builder()
					.key(key)
					.content(content)
					.locale(getProcessingLocaleString(locale))
					.build();
		} else {
			messageEntity.setContent(content);
		}
		messageSourceRepository.save(messageEntity);
	}

	@Override
	public List<MessageSourceEntity> getMessages(Locale locale) {
		return this.messageSourceRepository.findByLocale(getProcessingLocaleString(locale));
	}

	private String getProcessingLocaleString(Locale locale) {
		return locale.getLanguage();
	}

	@Override
	public Map<String, String> getMessagesMap(Locale locale) {
		List<MessageSourceEntity> messages = this.getMessages(locale);
		Map<String, String> messagesMap = ListUtility.createMap();
		for (MessageSourceEntity mse :messages) {
			messagesMap.put(mse.getKey(), mse.getContent());
		}
		return messagesMap;
	}
}
