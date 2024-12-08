package healthSafe.dvds20222cg4hce.domain.historia;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@RevisionEntity(MyRevisionListener.class)

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MyRevisionEntity extends DefaultRevisionEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6500668172049656123L;
	
	private String modifierUser;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyRevisionEntity other = (MyRevisionEntity) obj;
		return Objects.equals(modifierUser, other.modifierUser);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(modifierUser);
		return result;
	}
	
	

}
