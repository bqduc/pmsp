/**
 * 
 */
package net.paramount.security;

import lombok.Builder;
import net.paramount.security.base.Cryptographer;

/**
 * @author ducbq
 *
 */
@Builder
public class GlobalCryptogramRepository {
	public Cryptographer getCryptographer(CryptographyAlgorithm algorithm) {
		if (CryptographyAlgorithm.PLAIN_TEXT.equals(algorithm))
			return PlainTextCryptographer.builder().build();

		if (CryptographyAlgorithm.PRIVATE_ADVANCED.equals(algorithm))
			return AdvancedCryptographer.builder().build();

		if (CryptographyAlgorithm.PRIVATE_UNICORN.equals(algorithm))
			return UnicornCryptographer.builder().build();

		if (CryptographyAlgorithm.PRIVATE_HIGH.equals(algorithm))
			return PrivateHighCryptographer.builder().build();
		
		if (CryptographyAlgorithm.PRIVATE_MEDIUM.equals(algorithm))
			return MediumCryptographer.builder().build();

		if (CryptographyAlgorithm.PRIVATE_MEDIUM.equals(algorithm))
			return PrivateLowCryptographer.builder().build();

		return BasicCryptographer.builder().build();
	}
}
