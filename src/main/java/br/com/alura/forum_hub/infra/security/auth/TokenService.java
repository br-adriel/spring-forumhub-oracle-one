package br.com.alura.forum_hub.infra.security.auth;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.alura.forum_hub.domain.usuario.Usuario;

@Service
public class TokenService {
	@Value("${api.security.token.secret}")
	private String jwtSecret;

	@Value("${api.security.token.access-expiration-minutes}")
	private Integer accessTokenDuration;

	private String issuer = "API ForumHub";

	public String gerarToken(Usuario usuario) {
		try {
			var alg = Algorithm.HMAC256(jwtSecret);
			return JWT.create()
					.withIssuer(issuer)
					.withSubject(usuario.getEmail())
					.withExpiresAt(generateExpirationDate())
					.sign(alg);
		} catch (JWTCreationException ex) {
			throw new RuntimeException("Erro ao gerar token de acesso.", ex);
		}
	}

	public String getSubject(String token) {
		try {
			var algorithm = Algorithm.HMAC256(jwtSecret);
			return JWT.require(algorithm)
					.withIssuer(issuer)
					.build()
					.verify(token)
					.getSubject();
		} catch (JWTVerificationException exception) {
			throw new RuntimeException("Token inválido ou expirado.");
		}
	}

	private Instant generateExpirationDate() {
		return LocalDateTime
				.now()
				.plusMinutes(accessTokenDuration)
				.toInstant(ZoneOffset.of("-03:00"));
	}
}
