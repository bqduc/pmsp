/**
 * 
 */
package net.paramount.i18n;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.context.MessageSource;

/**
 * @author ducbq
 *
 */
public interface PersistenceMessageService extends MessageSource {
	Map<String, String> getMessagesMap(Locale locale);
	List<MessageSourceEntity> getMessages(Locale locale);
	void saveMessage(String key, String content, Locale locale);
}
