/**
 * 
 */
package net.paramount.autx;

import net.paramount.auth.component.JwtTokenProvider;
import net.paramount.auth.entity.UserAccount;
import net.paramount.framework.entity.auth.AuthenticationDetails;

/**
 * @author ducbq
 *
 */
public class TestEntry {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testJToken();
	}
	
	protected static void testJToken() {
		JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();

		AuthenticationDetails userDetails = new UserAccount();
		userDetails.setId(Long.valueOf(10292019));
		userDetails.setSsoId("bdq1hc");
		//String token = jwtTokenProvider.generateToken(userDetails);
		//System.out.println(token);

		AuthenticationDetails unmarshalledUserDetails = jwtTokenProvider.getUserDetailsFromJWT("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMDI5MjAxOSNiZHExaGMiLCJpYXQiOjE1ODMxNTYzMTYsImV4cCI6MTU4Mzc2MTExNn0.dRsYSBfXIKIxqzml462gA8me3FCzZNz77ZPMdAeuy4hznxZODJOjI1ssd0YW4bRsjA3_D5nCBJkR2Bgp1GAM-Q");
		System.out.println(unmarshalledUserDetails);
	}

}
