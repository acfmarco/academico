package br.com.iasc.academico.Entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TAB_STATUS_COBRANCA database table.
 * 
 */
@Entity
@Table(name="TAB_STATUS_COBRANCA")
public class StatusCobranca implements Serializable, BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TAB_STATUS_COBRANCA_GENERATOR", sequenceName="s_STATUS_COBRANCA_01", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TAB_STATUS_COBRANCA_GENERATOR")
	@Column(name="STCO_ID")
	private Long codigo;

	@Column(name="STCO_NOM")
	private String statusNome;

	@Column(name="STCO_IND_GER_NOV_COB")
	private Long indicadorGerarCobranca;
	
	@Column(name="STCO_IND_VER_PAG")
	private Long indicadorVerificaPagamento;
	

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getStatusNome() {
		return statusNome;
	}

	public void setStatusNome(String statusNome) {
		this.statusNome = statusNome;
	}

	public Long getIndicadorGerarCobranca() {
		return indicadorGerarCobranca;
	}

	public void setIndicadorGerarCobranca(Long indicadorGerarCobranca) {
		this.indicadorGerarCobranca = indicadorGerarCobranca;
	}
	
	public Long getIndicadorVerificaPagamento() {
		return indicadorVerificaPagamento;
	}

	public void setIndicadorVerificaPagamento(Long indicadorVerificaPagamento) {
		this.indicadorVerificaPagamento = indicadorVerificaPagamento;
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
		StatusCobranca other = (StatusCobranca) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StatusCobranca [codigo=" + codigo + ", statusNome=" + statusNome + ", indicadorGerarCobranca="
				+ indicadorGerarCobranca + "]";
	}
	
}