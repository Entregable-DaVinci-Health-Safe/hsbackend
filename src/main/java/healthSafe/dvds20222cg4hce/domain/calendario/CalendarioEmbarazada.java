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
@DiscriminatorValue("EMBARAZADA")
@Table(name="calendario_embarazadas")

@Data
@NoArgsConstructor(force = true)
@SuperBuilder
public class CalendarioEmbarazada extends Calendario implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -4084340145582865453L;

}
