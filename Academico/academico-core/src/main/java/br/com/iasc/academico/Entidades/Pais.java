package br.com.iasc.academico.Entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TBG_PAIS database table.
 * 
 */
@Entity
@Table(name="TBG_PAIS")
@NamedQuery(name="Pais.findAll", query="SELECT p FROM Pais p")
public class Pais implements Serializable, BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBG_PAIS_GENERATOR", sequenceName="S_PAIS_01", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBG_PAIS_GENERATOR")
	@Column(name="PAIS_ID")
	private Long codigo;

	@Column(name="PAIS_NOM")
	private String paisNome;

	@Column(name="PAIS_SIGLA")
	private String paisSigla;

	public Pais() {
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getPaisNome() {
		return paisNome;
	}

	public void setPaisNome(String paisNome) {
		this.paisNome = paisNome;
	}

	public String getPaisSigla() {
		return paisSigla;
	}

	public void setPaisSigla(String paisSigla) {
		this.paisSigla = paisSigla;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pais other = (Pais) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pais [codigo=" + codigo + ", paisNome=" + paisNome + ", paisSigla=" + paisSigla + "]";
	}
	
}