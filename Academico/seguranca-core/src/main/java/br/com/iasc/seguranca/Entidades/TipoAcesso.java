package br.com.iasc.seguranca.Entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SEG_TIPO_ACESSO database table.
 * 
 */
@Entity
@Table(name="SEG_TIPO_ACESSO")
@NamedQuery(name="TipoAcesso.findAll", query="SELECT t FROM TipoAcesso t")
public class TipoAcesso implements Serializable, BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEG_TIPO_ACESSO_GENERATOR", sequenceName="S_TP_ACESSO_01", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEG_TIPO_ACESSO_GENERATOR")
	@Column(name="TACE_ID")
	private Long codigo;

	@Column(name="TACE_DESC")
	private String taceDesc;

	@Column(name="TACE_NOM_ACESSO")
	private String taceNomAcesso;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getTaceDesc() {
		return taceDesc;
	}

	public void setTaceDesc(String taceDesc) {
		this.taceDesc = taceDesc;
	}

	public String getTaceNomAcesso() {
		return taceNomAcesso;
	}

	public void setTaceNomAcesso(String taceNomAcesso) {
		this.taceNomAcesso = taceNomAcesso;
	}
	
}