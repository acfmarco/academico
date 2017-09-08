package br.com.iasc.academico.Entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TAB_CURSO database table.
 * 
 */
@Entity
@Table(name="TAB_CURSO")
@NamedQuery(name="Curso.findAll", query="SELECT c FROM Curso c")
public class Curso implements Serializable, BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TAB_CURSO_GENERATOR", sequenceName="S_CURSO_01", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TAB_CURSO_GENERATOR")
	@Column(name="CRUS_ID")
	private Long codigo;

	@Column(name="CRUS_NOM")
	private String crusNome;

	@Column(name="CRUS_SIGLA")
	private String crusSigla;

	public Curso() {
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getCrusNome() {
		return this.crusNome;
	}

	public void setCrusNome(String crusNome) {
		this.crusNome = crusNome;
	}

	public String getCrusSigla() {
		return this.crusSigla;
	}

	public void setCrusSigla(String crusSigla) {
		this.crusSigla = crusSigla;
	}

}