/**
 * 
 */
package net.paramount.auth.component;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import net.paramount.auth.entity.UserAccount;
import net.paramount.framework.component.CompCore;
import net.paramount.framework.entity.auth.AuthenticationDetails;

/**
 * @author ducbq
 *
 */
@Component
public class JwtTokenProvider extends CompCore {
	private static final long serialVersionUID = -312627269682252483L;

	private static final String TOKEN_SUBJECT_SEPARATOR = "#";
  // Đoạn JWT_SECRET này là bí mật, chỉ có phía server biết
  private final String JWT_SECRET = "lodaaaaaa";

  //Thời gian có hiệu lực của chuỗi jwt
  private final long JWT_EXPIRATION = 604800000L;

  // Tạo ra jwt từ thông tin user
  public String generateToken(AuthenticationDetails userDetails) {
      Date now = new Date();
      Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
      // Tạo chuỗi json web token từ id của user.
      return Jwts.builder()
                 .setSubject(marshall(userDetails))
                 .setIssuedAt(now)
                 .setExpiration(expiryDate)
                 .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                 .compact();
  }

  private String marshall(AuthenticationDetails userDetails) {
  	return new StringBuilder(Long.toString(userDetails.getId()))
  			.append(TOKEN_SUBJECT_SEPARATOR)
  			.append(userDetails.getSsoId())
  			.toString();
  }

  private AuthenticationDetails unmarshall(String source) {
  	AuthenticationDetails userDetails = null;
  	if (!source.contains(TOKEN_SUBJECT_SEPARATOR))
  		return null;

  	userDetails = initiateUserDetails();
  	String[] parts = source.split(TOKEN_SUBJECT_SEPARATOR);
  	userDetails.setId(Long.valueOf(parts[0]));
  	userDetails.setSsoId(parts[1]);
  	return userDetails;
	}

  private AuthenticationDetails initiateUserDetails() {
  	AuthenticationDetails userDetails = new UserAccount();
  	return userDetails;
  }

  public AuthenticationDetails getUserDetailsFromJWT(String token) {
    Claims claims = Jwts.parser()
        .setSigningKey(JWT_SECRET)
        .parseClaimsJws(token)
        .getBody();

    return unmarshall(claims.getSubject());
  }

  // Lấy thông tin user từ jwt
  /*
  public Long getUserIdFromJWT(String token) {
      Claims claims = Jwts.parser()
                          .setSigningKey(JWT_SECRET)
                          .parseClaimsJws(token)
                          .getBody();

      return Long.parseLong(claims.getSubject());
  }
  */

  public boolean validateToken(String authToken) {
      try {
          Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
          return true;
      } catch (MalformedJwtException ex) {
          log.error("Invalid JWT token");
      } catch (ExpiredJwtException ex) {
          log.error("Expired JWT token");
      } catch (UnsupportedJwtException ex) {
          log.error("Unsupported JWT token");
      } catch (IllegalArgumentException ex) {
          log.error("JWT claims string is empty.");
      }
      return false;
  }
}
