package healthSafe.dvds20222cg4hce.domain.prescripcion;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="adjuntos")

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Adjunto implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -5927960105836101253L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "adj_id")
	private Long id;
	
	@Column(name = "adj_uri")
	private String uri;

}
