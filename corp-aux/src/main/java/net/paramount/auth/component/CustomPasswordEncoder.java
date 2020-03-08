/**
 * 
 */
package net.paramount.auth.component;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import net.paramount.auth.model.CryptoAlgorithm;
import net.paramount.common.CommonUtility;

/**
 * @author bqduc
 *
 */
//@SuppressWarnings("deprecation")
@Named("virtualEncoder")
@Component
public class CustomPasswordEncoder implements SimplePasswordEncoder {
	private static final String PASSWORD_ENCODER_SALT = "裴达克·奎-裴达克·奎";

	@Inject
	private PasswordEncoder passwordEncoder;

	@Override
	public String encode(CharSequence plainTextPassword) {
		PasswordEncoder processPasswordEncoder = getPasswordEncoder();
		String upgradePassword = new StringBuilder(plainTextPassword).append(PASSWORD_ENCODER_SALT).toString();
		return processPasswordEncoder.encode(upgradePassword);
		/**
		 * Original
		return passwordEncoder.encode(rawPassword);
		*/
		//return new Md5PasswordEncoder().encodePassword(rawPassword.toString(), PASSWORD_ENCODER_SALT);
	}

	@Override
	public boolean matches(CharSequence plainTextPassword, String encodedPassword) {
		String upgradedPassword = this.encode(plainTextPassword);
		return passwordEncoder.matches(upgradedPassword, encodedPassword);
		//return new Md5PasswordEncoder().encodePassword(rawPassword.toString(), PASSWORD_ENCODER_SALT).equals(encodedPassword);
	}

	public boolean comparePassword(String userPassword, String repositoryPassword, String algorithm) {
		if (CommonUtility.isEmpty(algorithm)) {
			return repositoryPassword.equals(passwordEncoder.encode(userPassword));
		}

		if (CryptoAlgorithm.PLAIN_TEXT.getAlgorithm().equalsIgnoreCase(algorithm)){
			return userPassword.equals(repositoryPassword);
		}

		//String encodedPwd = this.performEncode(userPassword);
		return repositoryPassword.equals(passwordEncoder.encode(userPassword));
	}
	
	private PasswordEncoder getPasswordEncoder() {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder;
	}
}
