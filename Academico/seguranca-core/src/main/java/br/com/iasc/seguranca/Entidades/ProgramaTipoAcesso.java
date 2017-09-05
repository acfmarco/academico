package br.com.iasc.seguranca.Entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SEG_PROGRAMA_TIPO_ACESSO database table.
 * 
 */
@Entity
@Table(name="SEG_PROGRAMA_TIPO_ACESSO")
@NamedQuery(name="ProgramaTipoAcesso.findAll", query="SELECT p FROM ProgramaTipoAcesso p")
public class ProgramaTipoAcesso implements Serializable, BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEG_PROGRAMA_TIPO_ACESSO_GENERATOR", sequenceName="S_PROG_TP_ACESSO_01", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEG_PROGRAMA_TIPO_ACESSO_GENERATOR")
	@Column(name="PTAC_ID")
	private Long codigo;

	@Column(name="PTAC_IND_CONSULTA")
	private BigDecimal ptacIndConsulta;

	@Column(name="PTAC_IND_EDITA")
	private BigDecimal ptacIndEdita;

	@Column(name="PTAC_IND_EXCLUI")
	private BigDecimal ptacIndExclui;

	@Column(name="PTAC_IND_INSERE")
	private BigDecimal ptacIndInsere;

	//bi-directional many-to-one association to Programa
	@ManyToOne
	@JoinColumn(name="PROG_ID")
	private Programa programa;

	//bi-directional many-to-one association to TipoAcesso
	@ManyToOne
	@JoinColumn(name="TACE_ID")
	private TipoAcesso tipoAcesso;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public BigDecimal getPtacIndConsulta() {
		return ptacIndConsulta;
	}

	public void setPtacIndConsulta(BigDecimal ptacIndConsulta) {
		this.ptacIndConsulta = ptacIndConsulta;
	}

	public BigDecimal getPtacIndEdita() {
		return ptacIndEdita;
	}

	public void setPtacIndEdita(BigDecimal ptacIndEdita) {
		this.ptacIndEdita = ptacIndEdita;
	}

	public BigDecimal getPtacIndExclui() {
		return ptacIndExclui;
	}

	public void setPtacIndExclui(BigDecimal ptacIndExclui) {
		this.ptacIndExclui = ptacIndExclui;
	}

	public BigDecimal getPtacIndInsere() {
		return ptacIndInsere;
	}

	public void setPtacIndInsere(BigDecimal ptacIndInsere) {
		this.ptacIndInsere = ptacIndInsere;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public TipoAcesso getTipoAcesso() {
		return tipoAcesso;
	}

	public void setTipoAcesso(TipoAcesso tipoAcesso) {
		this.tipoAcesso = tipoAcesso;
	}
	
}