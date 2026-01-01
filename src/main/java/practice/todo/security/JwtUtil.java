package practice.todo.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private static final String SECRET_KEY =
			"uhgeihiueh8293475827095230qwkhtl#*#&98";
	private static final long EXPIRATION_TIME = 1000*60*2; //2min

	private Key getSigningKey() {
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	}

	public String generateToken(String username) {

		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(
						new Date(System.currentTimeMillis() + EXPIRATION_TIME)
						)
				.signWith(getSigningKey(), SignatureAlgorithm.HS256)
				.compact();
	}
}
