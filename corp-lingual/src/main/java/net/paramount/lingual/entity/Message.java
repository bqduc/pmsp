/**
 * 
 */
package net.paramount.lingual.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import net.paramount.domain.entity.general.Language;
import net.paramount.framework.entity.Auditable;

/**
 * @author ducbq
 *
 */
@Builder
@Entity
@Table(name = "resx_message")
@EqualsAndHashCode(callSuper = true)
public class Message extends Auditable <String>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2177334701992871126L;

	@ManyToOne
	@JoinColumn(name = "label_id")
	private Label label;

	@ManyToOne
	@JoinColumn(name = "language_id")
	private Language language;

	@Column(name = "value")
	private String value;

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
