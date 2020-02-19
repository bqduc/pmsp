/**
 * 
 */
package net.paramount.security;

import lombok.Builder;
import net.paramount.framework.security.Cryptographer;

/**
 * @author ducbq
 *
 */
@Builder
public class PlainTextCryptographer extends CryptographyBase implements Cryptographer {
	private static final long serialVersionUID = 3762580979910681616L;

	@Override
	public String stringEncode(String plainText) {
		return super.addSalts(plainText);
	}

	@Override
	public String stringDecode(String encodedText) {
		return super.eliminateSalts(encodedText);
	}
}
