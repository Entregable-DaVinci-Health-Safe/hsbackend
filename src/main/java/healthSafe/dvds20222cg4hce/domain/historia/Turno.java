package healthSafe.dvds20222cg4hce.domain.historia;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="turnos")

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Turno implements Serializable{

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "trn_id")
	private Long id;

    @Column(name = "trn_fecha_inicio")
	private Long fechaInicio;

    @Column(name = "trn_direccion")
	private String direccion;

    @Column(name = "trn_profesional")
	private String profesional;

	@Column(name = "trn_especialidad")
	private String especialidad;

    @Column(name = "trn_institucion")
	private String institucion;

    @Column(name = "trn_motivo")
	private String motivo;

    @Column(name = "trn_google_id")
	private String googleId;

    @Column(name = "trn_activo")
	private Boolean activo;

    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="trn_hta_id", referencedColumnName="hta_id", nullable = false)
	@JsonBackReference
	private HistoriaMedica historiaMedica;
}
