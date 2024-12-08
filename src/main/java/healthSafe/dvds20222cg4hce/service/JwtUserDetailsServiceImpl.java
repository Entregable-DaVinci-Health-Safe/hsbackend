package healthSafe.dvds20222cg4hce.service;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import healthSafe.dvds20222cg4hce.domain.usuario.Usuario;
import healthSafe.dvds20222cg4hce.repository.usuario.UsuarioRepository;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService{
	
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	public JwtUserDetailsServiceImpl(final UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<Usuario> usuarioOptional = usuarioRepository.findByMail(mail);
		if(usuarioOptional.isPresent()) {
			return new User(usuarioOptional.get().getMail(), 
					usuarioOptional.get().getPassword(), 
					new ArrayList<>());
		}
		
		throw new UsernameNotFoundException("User not found with mail: " + mail);
	}
	
}
