package healthSafe.dvds20222cg4hce.domain.autorizacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.fasterxml.jackson.annotation.JsonIgnore;

import healthSafe.dvds20222cg4hce.domain.usuario.Usuario;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@PrimaryKeyJoinColumn(name = "acp_id")
@DiscriminatorValue("AUTORIZACION")
@Table(name="autorizaciones")

@Data
@NoArgsConstructor(force = true)
@SuperBuilder
public class Autorizacion extends AutorizacionComponent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 579469905174856115L;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "autorizaciones_composiciones",
	joinColumns = {@JoinColumn(name = "acm_aut_id")},
	inverseJoinColumns = {@JoinColumn(name = "acm_acp_id")})
	@JsonIgnore
	@NotAudited
	private List<AutorizacionComponent> componentes;
	
	public void addComponente(AutorizacionComponent componente) {
		if(this.componentes == null) componentes = new ArrayList<AutorizacionComponent>();
		componentes.add(componente);
	}
	
	public void removeComponente(AutorizacionComponent componente) {
		componentes.remove(componente);
	}

}
