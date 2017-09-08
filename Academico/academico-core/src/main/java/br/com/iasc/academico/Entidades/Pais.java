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
	
}