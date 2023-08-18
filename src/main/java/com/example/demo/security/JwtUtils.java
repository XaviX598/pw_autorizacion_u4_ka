package com.example.demo.security;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

//clase que nos permite crear un token
@Component
public class JwtUtils {

//	@Autowired
//	private IParametrosService parametros
	// el authentication de org.springframework.security.core.Authentication; y el
	// segundo es payload

	// para usar valores que estan en propierties

	@Value("${app.jwt.secret}")
	private String jwtSecret;
	@Value("${app.jwt.expiration.ms}") // para inyectar los valores liego () luego "" luego {} y dentro el valor
	private int jwtExpiration;

	private static final Logger LOG = LoggerFactory.getLogger(JwtUtils.class);

	public String generateJwtToken( String nombre) {

//		this.parametros.consultarValor("tiempo");
//		this.parametros.consultarValor("semilla");

		LOG.info("semilla: " + jwtSecret + "tiempo: " + jwtExpiration);

		return Jwts.builder().setSubject(nombre).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + this.jwtExpiration))
				.signWith(SignatureAlgorithm.HS512, this.jwtSecret).compact();

		// subject el nombre del usuario, issuedad, el tiempo de expiracion, no se puede
	}

}
