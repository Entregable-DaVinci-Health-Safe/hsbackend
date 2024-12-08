package healthSafe.dvds20222cg4hce.domain.calendario;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@PrimaryKeyJoinColumn(name = "cld_id")
@DiscriminatorValue("ADULTO")
@Table(name="calendario_adultos")

@Data
@NoArgsConstructor(force = true)
@SuperBuilder
public class CalendarioAdulto extends Calendario implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -3189218755661617075L;

}
