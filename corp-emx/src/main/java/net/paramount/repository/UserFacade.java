/*
package net.paramount.repository;


import java.util.List;

import org.springframework.stereotype.Component;

import net.paramount.auth.entity.AuxUserProfile;
import net.paramount.framework.repository.BaseDAO;

*//**
 * 
 * @author MOHAMMED BOUNAGA
 * 
 * github.com/medbounaga
 *//*

@Component//@Stateless
public class UserFacade extends BaseDAO{
    *//**
	 * 
	 *//*
	private static final long serialVersionUID = -8466731564400416922L;


		public AuxUserProfile create(AuxUserProfile entity) {
        em.persist(entity);
        return entity;
    }
    

    public AuxUserProfile update(AuxUserProfile entity) {
        em.merge(entity);
        return entity;
    }

    public void remove(AuxUserProfile entity) {
        em.remove(em.merge(entity));
    }

    public AuxUserProfile find(Object id) {
        return em.find(AuxUserProfile.class, id);
    }

    
    public List<AuxUserProfile> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(AuxUserProfile.class));
        return em.createQuery(cq).getResultList();
    }

}
*/