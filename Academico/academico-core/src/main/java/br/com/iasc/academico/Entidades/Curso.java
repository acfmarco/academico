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
	@Column(name="CURS_ID")
	private Long codigo;

	@Column(name="CURS_NOM")
	private String cursNome;

	@Column(name="CURS_SIGLA")
	private String cursSigla;

	public Curso() {
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getCursNome() {
		return cursNome;
	}

	public void setCursNome(String cursNome) {
		this.cursNome = cursNome;
	}

	public String getCursSigla() {
		return cursSigla;
	}

	public void setCursSigla(String cursSigla) {
		this.cursSigla = cursSigla;
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
		Curso other = (Curso) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Curso [codigo=" + codigo + ", cursNome=" + cursNome + ", cursSigla=" + cursSigla + "]";
	}
	
}