/**
 * 
 */
package net.paramount.lingual.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import net.paramount.framework.entity.Auditable;

/**
 * @author ducbq
 *
 */
@Builder
@Entity
@Table(name = "resx_label")
@EqualsAndHashCode(callSuper = true)
public class Label extends Auditable <String>{
	private static final long serialVersionUID = 2794766451650834594L;

	@Column(name = "name", unique = true, length=100)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
