/**
 * 
 */
package net.paramount.i18n;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import net.paramount.framework.entity.ObjectBase;

/**
 * @author ducbq
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "message_source_entry")
public class MessageSourceEntity extends ObjectBase {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5150863975442948556L;

	@Column(length = 5)
	private String locale;

	@Column(name = "key", length = 150)
	private String key;

	@Column(name = "content")
	private String content;

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
