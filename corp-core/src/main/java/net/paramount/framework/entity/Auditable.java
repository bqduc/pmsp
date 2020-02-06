/**
 * 
 */
package net.paramount.framework.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author ducbq
 *
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<T> extends ObjectBase {
  /**
	 * 
	 */
	private static final long serialVersionUID = -4047672987189874306L;

	@CreatedBy
  @Column(name="created_by")
  protected T createdBy;

  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name="creation_date")
  protected Date creationDate;

  @LastModifiedBy
  @Column(name="last_modified_by")
  private T lastModifiedBy;

  @LastModifiedDate
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name="last_modified_date")
  protected Date lastModifiedDate;

	public T getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(T createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public T getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(T lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

}
