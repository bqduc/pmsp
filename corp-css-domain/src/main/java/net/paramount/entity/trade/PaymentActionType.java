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

package net.paramount.entity.trade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import net.paramount.framework.entity.AuditBase;

/**
 * Ödeme Hareket Tip Tanımları
 * 
 * @author volkan
 *
 */
@Entity
@Table(name="PAYMENT_ACTION_TYPE")
public class PaymentActionType extends AuditBase implements Serializable {

	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator="genericSeq")
    @Column(name="ID")
	private Long id;
	
    @Column(name="CODE", length=20, unique=true, nullable=false)
    @Size(max=20, min=1)
    @NotNull
	private String code;

    @Column(name="NAME", length=50)
    @Size(max=50)
    private String name;

    @Column(name="INFO")
	private String info;

    @Column(name="SYSTEM")
    private Boolean system = Boolean.FALSE;

    @Column(name="ISACTIVE")
	private Boolean active = Boolean.TRUE;

    @Column(name="WEIGHT")
    private Integer weight ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
	
	@Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaymentActionType)) {
            return false;
        }
        PaymentActionType other = (PaymentActionType)object;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) return false;
        return true;
    }
	
	@Override
    public String toString() {
        return "com.ut.tekir.entities.PaymentActionType[id=" + id + "]";
    }

   	
}
