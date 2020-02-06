/*
 * Copyleft 2007-2011 Ozgur Yazilim A.S.
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 * http://www.gnu.org/licenses/lgpl.html
 *
 * www.tekir.com.tr
 * www.ozguryazilim.com.tr
 *
 */

package net.paramount.entity.general;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Builder;
import net.paramount.framework.entity.Auditable;

/**
 * Entity class Country
 * 
 * @author haky
 */
@Builder
@Entity
@Table(name = "country")
public class Country extends Auditable<String> {
	private static final long serialVersionUID = -8233303460213700583L;

	@Column(name = "code", nullable = false, unique = true, length = 2)
	private String code;

	@Column(name = "iso_code", nullable = false, unique = true, length = 3)
	private String isoCode;

	@Column(name = "iso_language_code", length = 2)
	private String isoLanguageCode;

	@Column(name = "name", length = 50)
	private String name;

	@Column(name = "name_local", length = 50)
	private String nameLocal;

	@Lob
	@Column(name = "info", columnDefinition="TEXT")
	private String info;

	@Column(name = "system")
	private Boolean system;

	@Builder.Default
	@Column(name = "is_active")
	private Boolean active = Boolean.FALSE;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getSystem() {
		return system;
	}

	public void setSystem(Boolean system) {
		this.system = system;
	}

	public String getIsoCode() {
		return isoCode;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}

	public String getNameLocal() {
		return nameLocal;
	}

	public void setNameLocal(String nameLocal) {
		this.nameLocal = nameLocal;
	}

	public String getIsoLanguageCode() {
		return isoLanguageCode;
	}

	public void setIsoLanguageCode(String isoLanguageCode) {
		this.isoLanguageCode = isoLanguageCode;
	}
}
