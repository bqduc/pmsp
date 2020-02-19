/*
package net.paramount.repository;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

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
public class LoginFacade extends BaseDAO{
    *//**
	 * 
	 *//*
	private static final long serialVersionUID = -679206037743344830L;

		public AuxUserProfile userExist(String username, String password) {

        TypedQuery<AuxUserProfile> query = em.createQuery("SELECT u FROM UserProfile u WHERE u.authAccount.ssoId = :login AND u.authAccount.password = :password AND u.active = true", AuxUserProfile.class);

        query.setParameter("login", username);
        query.setParameter("password", password);
        try {
            AuxUserProfile user = query.getSingleResult();
            System.out.println("User exists");
            return user;
        } catch (NoResultException e) {
            System.out.println("User doesn't exist");
            return null;
        }
    }


}
*/