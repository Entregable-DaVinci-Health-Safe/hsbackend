package healthSafe.dvds20222cg4hce.jwtutils;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.gson.GsonFactory;

import healthSafe.dvds20222cg4hce.domain.usuario.Usuario;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenManager {

	@Value("${google.client.client-id}")
	private String clientId;

	private String publicKeySTR;

	private static final long JWT_TOKEN_VALIDITY = 360;

	private PublicKey loadPublicKey(String key) throws Exception {
		byte[] encodedKey = Base64.getDecoder().decode(key);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encodedKey);
        
        return (RSAPublicKey) keyFactory.generatePublic(keySpec);
    }

	private PrivateKey loadPrivateKey(String key) throws Exception {
		byte[] encodedKey = Base64.getDecoder().decode(key);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encodedKey);
		return keyFactory.generatePrivate(keySpec);
	}
	
	public String generateToken(Usuario usuario) throws Exception {
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        KeyPair kp = kpg.generateKeyPair();

        publicKeySTR = Base64.getEncoder().encodeToString(kp.getPublic().getEncoded());
        String privateKeySTR = Base64.getEncoder().encodeToString(kp.getPrivate().getEncoded());

		PrivateKey privateKey = loadPrivateKey(privateKeySTR);
		Map<String, Object> claims = new HashMap<>();
		return Jwts.builder().setClaims(claims).setSubject(usuario.getMail())
				.setIssuer("accounts.health.com")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 10000))
				.signWith(SignatureAlgorithm.RS256, privateKey).compact();
	}
	
	public Claims getClaimsFromJwtToken(String token) throws Exception{
		PublicKey publicKey = loadPublicKey(publicKeySTR);
		Claims claims = null;
		claims = Jwts.parser().setSigningKey(publicKey)
				.parseClaimsJws(token).getBody();

		return claims;
		
	}
	
	public Boolean validateJwtToken(String token, UserDetails userDetails) throws BusinessException {
		try {
			Claims claims = getClaimsFromJwtToken(token);
			String mail = claims.getSubject();
			Boolean isTokenExpired = claims.getExpiration().before(new Date());
			return (mail.equals(userDetails.getUsername()) && !isTokenExpired);
		}catch(IllegalArgumentException e) {
			return false;
		}catch(Exception e){
			e.printStackTrace();
			throw new BusinessException("No se pudo obtener los claims");
		}
		
	}

	public Payload getPayloadFromGoogleToken(String token) {
		Payload payload  = null;
		HttpTransport httpTransport = null;
		try {httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		} catch (GeneralSecurityException | IOException e1) {e1.printStackTrace();}

		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(httpTransport, GsonFactory.getDefaultInstance())
				   .setAudience(Collections.singletonList(clientId))
				   .build();
		try {
			GoogleIdToken idToken = verifier.verify(token);
			if (idToken != null) {payload = idToken.getPayload();}
		} catch (GeneralSecurityException | IOException e) {e.printStackTrace();}
		return payload;
	}
	
	public Boolean validateGoogleToken(String token, UserDetails userDetails) {
		Payload payload = getPayloadFromGoogleToken(token);
		Boolean notTokenExpired = new Date(payload.getExpirationTimeSeconds()).before(new Date());
		return (payload.getEmail().equals(userDetails.getUsername()) && notTokenExpired);
	}
}
