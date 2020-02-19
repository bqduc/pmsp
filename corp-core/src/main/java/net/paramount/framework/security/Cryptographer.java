/**
 * 
 */
package net.paramount.framework.security;

import net.paramount.exceptions.CryptographyException;

/**
 * @author ducbq
 *
 */
public interface Cryptographer {
	byte[] encode(String plainText) throws CryptographyException;
	byte[] decode(byte[] encodedBytes) throws CryptographyException;

	String stringEncode(String plainText) throws CryptographyException;
	String stringDecode(String encodedBuffer) throws CryptographyException;
}
