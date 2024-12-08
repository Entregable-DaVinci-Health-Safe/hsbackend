package healthSafe.dvds20222cg4hce.jwtutils;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;

import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.service.JwtUserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;


@Component
public class JwtFilter extends OncePerRequestFilter{	
	
	@Autowired
	private JwtUserDetailsServiceImpl jwtUserDetailsService;
	
	@Autowired
	private TokenManager tokenManager;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String tokenHeader = request.getHeader("Authorization");
		String mail = null;
		String token = null;
		try{
			if(tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
				token = tokenHeader.substring(7);
				mail = getMailFromToken(token);

			}else { System.out.println("Bearer String not found in token"); }
		
			if(mail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(mail);
				
				if(isTokenValid(token, userDetails)) {
					setSecurityContext(request, userDetails);
				}else {
					System.out.println("JWT Token has expired");
				}
			}else {
				System.out.println("No se pudo validar el mail");
			}
		}catch(Exception e){
			System.out.println("Error en token");
		}
		filterChain.doFilter(request, response);
	}
	
	private void setSecurityContext(HttpServletRequest request, UserDetails userDetails) {
		UsernamePasswordAuthenticationToken authToken = 
				new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		
		authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authToken);
	}
	
	private Boolean isTokenValid(String token, UserDetails userDetails) throws Exception {
		Boolean isTokenValid = false;

		try {
			isTokenValid = tokenManager.validateJwtToken(token, userDetails);
		} catch (BusinessException e) {
			isTokenValid = tokenManager.validateGoogleToken(token, userDetails);
		}
		return isTokenValid;
	}
	
	public String getMailFromToken(String token) {
		String mail = null;
		try {mail = tokenManager.getClaimsFromJwtToken(token).getSubject();
		}catch(IllegalArgumentException e) {e.printStackTrace();
		}catch(ExpiredJwtException e) {e.printStackTrace();
		}catch(NullPointerException e) {e.printStackTrace();
		}catch(Exception e) {e.printStackTrace();}

		if(mail == null){
			Payload payload = tokenManager.getPayloadFromGoogleToken(token);
			if(payload != null){
				mail = payload.getEmail();
			}
		}

		return mail;
	}

}
