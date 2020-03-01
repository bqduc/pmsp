/**
 * 
 */
package net.paramount.framework.repository;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.paramount.framework.entity.ObjectBase;
import net.paramount.framework.service.ServiceImpl;

/**
 * @author ducbq
 *
 */

public abstract class BaseDAO<EntityType extends ObjectBase, Key extends Serializable> extends ServiceImpl<EntityType, Key> {
	private static final long serialVersionUID = -3317395762045169767L;

	@PersistenceContext /* (unitName = GlobalConstants.APPLICATION_PERSISTENCE_UNIT) */
	protected EntityManager em;

	@Override
	protected BaseRepository<EntityType, Key> getRepository() {
		return null;
	}
}
