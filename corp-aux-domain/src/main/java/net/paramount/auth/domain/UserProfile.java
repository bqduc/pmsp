/**
 * 
 */
package net.paramount.auth.domain;

import java.io.Serializable;

import lombok.Builder;
import net.paramount.auth.entity.UserAccount;

/**
 * @author ducbq
 *
 */
@Builder
public class UserProfile implements Serializable{
	private static final long serialVersionUID = 319145167472878337L;

	private UserAccount userAccount;
	private String displayName;

	public boolean isPresentUserAccount() {
		return (null != this.userAccount);
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public String getDisplayName() {
		if (null != this.userAccount)
			return this.userAccount.getDisplayName();

		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
