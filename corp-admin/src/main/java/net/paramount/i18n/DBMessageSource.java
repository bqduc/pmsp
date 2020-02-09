/**
 * 
 */
package net.paramount.i18n;

import java.text.MessageFormat;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.stereotype.Component;

/**
 * @author ducbq
 *
 */
/**
@Component("messageSource")
public class DBMessageSource extends AbstractMessageSource {
	@Autowired
	private LanguageEntityRepository languageRepository;
	
	private static final String DEFAULT_LOCALE_CODE = "en";

	@Override
	protected MessageFormat resolveCode(String key, Locale locale) {
		System.out.println("key: " + key);
		MessageSourceEntity message = languageRepository.findByKeyAndLocale(key,locale.getLanguage());
		if (message == null) {
			message = languageRepository.findByKeyAndLocale(key,DEFAULT_LOCALE_CODE);
		}
		if (null != message) {
			return new MessageFormat(message.getContent(), locale);
		} else {
			return new MessageFormat("n.a", locale);
		}
	}
}
*/
