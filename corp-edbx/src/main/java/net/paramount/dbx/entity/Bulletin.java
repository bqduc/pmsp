/**
 * 
 */
package net.paramount.dbx.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import net.paramount.dbx.enums.ComponentType;
import net.paramount.framework.entity.BizObjectBase;
import net.paramount.global.GlobalConstants;

/**
 * @author bqduc
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "edb_bulletin")
public class Bulletin extends BizObjectBase{
	private static final long serialVersionUID = 5462810149346361246L;

	@Column(name = "serial", length=GlobalConstants.SIZE_SERIAL)
	private String serial;

	@Column(name = "title", length=GlobalConstants.SIZE_NAME_SHORT)
	private String title;

	@Column(name = "published_date")
	private Date publishedDate;

	@Column(name = "reviewed_date", length=50)
	private Date reviewedDate;

	@Column(name = "reviewed_by", length=50)
	private String reviewedBy;

	@Column(name = "level", length=10)
	private String level;

	@Column(name = "item_code_group", length=GlobalConstants.SIZE_CODE)
	private String itemCodeGroup;

	@Column(name = "item_code_category", length=GlobalConstants.SIZE_CODE)
	private String itemCodeCategory;

	@Column(name = "source_type")
	@Enumerated(EnumType.ORDINAL)
  private ComponentType sourceType;

	@ManyToOne
	@JoinColumn(name = "parent_id")
	private Bulletin parent;

	@Column(name = "contents", columnDefinition="TEXT")
	private String contents;

	@Column(name = "sender", length=GlobalConstants.SIZE_CODE)
	private String sender;

	public Bulletin getParent() {
		return parent;
	}

	public void setParent(Bulletin parent) {
		this.parent = parent;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public ComponentType getSourceType() {
		return sourceType;
	}

	public void setSourceType(ComponentType sourceType) {
		this.sourceType = sourceType;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public Date getReviewedDate() {
		return reviewedDate;
	}

	public void setReviewedDate(Date reviewedDate) {
		this.reviewedDate = reviewedDate;
	}

	public String getReviewedBy() {
		return reviewedBy;
	}

	public void setReviewedBy(String reviewedBy) {
		this.reviewedBy = reviewedBy;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getItemCodeGroup() {
		return itemCodeGroup;
	}

	public void setItemCodeGroup(String itemCodeGroup) {
		this.itemCodeGroup = itemCodeGroup;
	}

	public String getItemCodeCategory() {
		return itemCodeCategory;
	}

	public void setItemCodeCategory(String itemCodeCategory) {
		this.itemCodeCategory = itemCodeCategory;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

}
