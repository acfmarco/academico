package br.com.iasc.academico.Entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the TBG_ESTADO database table.
 * 
 */
@Entity
@Table(name = "TBG_ESTADO")
@NamedQuery(name = "Estado.findAll", query = "SELECT e FROM Estado e")
public class Estado implements Serializable, BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "TBG_ESTADO_GENERATOR", sequenceName = "S_ESTADO_01", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TBG_ESTADO_GENERATOR")
	@Column(name = "ESTA_ID")
	private Long codigo;

	@Column(name = "ESTA_NOM")
	private String estaNome;

	@Column(name = "ESTA_SIGLA")
	private String estaSigla;

	// bi-directional many-to-one association to Pais
	@ManyToOne
	@JoinColumn(name = "PAIS_ID")
	private Pais pais;

	public Estado() {
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getEstaNome() {
		return estaNome;
	}

	public void setEstaNome(String estaNome) {
		this.estaNome = estaNome;
	}

	public String getEstaSigla() {
		return estaSigla;
	}

	public void setEstaSigla(String estaSigla) {
		this.estaSigla = estaSigla;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
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
		Estado other = (Estado) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Estado [codigo=" + codigo + ", estaNome=" + estaNome + ", estaSigla=" + estaSigla + ", pais=" + pais
				+ "]";
	}

}