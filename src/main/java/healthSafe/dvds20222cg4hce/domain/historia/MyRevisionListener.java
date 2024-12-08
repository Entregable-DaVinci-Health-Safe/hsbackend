package healthSafe.dvds20222cg4hce.domain.historia;

import java.util.Optional;

import org.hibernate.envers.RevisionListener;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class MyRevisionListener implements RevisionListener{

	@Override
	public void newRevision(Object revisionEntity) {
		// TODO Auto-generated method stub
		MyRevisionEntity myRevisionEntity = (MyRevisionEntity) revisionEntity;
		myRevisionEntity.setModifierUser(getUsername());
	}
	
	private String getUsername() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (principal instanceof User) {
			username = ((User)principal).getUsername();
		} else {
			username = principal.toString();
		}
		return username;
	}

}
