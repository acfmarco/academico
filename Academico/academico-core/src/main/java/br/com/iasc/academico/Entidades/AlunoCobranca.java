package br.com.iasc.academico.Entidades;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import javax.persistence.*;

import br.com.iasc.infra.repository.dto.ControleCobrancaDTO;

import java.util.Date;


/**
 * The persistent class for the TAB_ALUNO_COBRANCA database table.
 * 
 */
@Entity
@Table(name="TAB_ALUNO_COBRANCA")
@NamedQuery(name="AlunoCobranca.findAll", query="SELECT a FROM AlunoCobranca a")
public class AlunoCobranca implements Serializable, BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TAB_ALUNO_COBRANCA_GENERATOR", sequenceName="S_ALUNO_COBRANCA_01", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TAB_ALUNO_COBRANCA_GENERATOR")
	@Column(name="ALCO_ID")
	private Long codigo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ALCO_DAT_VENC")
	private Date alcoDataVencimento;

	@Column(name="ALCO_MES_REF")
	private Long alcoMesReferencia;

	@ManyToOne
	@JoinColumn(name="STCO_ID")
	private StatusCobranca statusCobranca;

	@ManyToOne
	@JoinColumn(name="ALUN_ID")
	private Aluno aluno;

	@Column(name="COBN_ID")
	private Long cobnId;
	
	@Transient
	private String dscDataVencimento;
	
	@Transient
	private ControleCobrancaDTO controleCobrancaDTO;
	
	@Transient
	private String dscDataPagamento;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Date getAlcoDataVencimento() {
		return alcoDataVencimento;
	}

	public void setAlcoDataVencimento(Date alcoDataVencimento) {
		this.alcoDataVencimento = alcoDataVencimento;
	}

	public Long getAlcoMesReferencia() {
		return alcoMesReferencia;
	}

	public void setAlcoMesReferencia(Long alcoMesReferencia) {
		this.alcoMesReferencia = alcoMesReferencia;
	}
	
	public StatusCobranca getStatusCobranca() {
		return statusCobranca;
	}

	public void setStatusCobranca(StatusCobranca statusCobranca) {
		this.statusCobranca = statusCobranca;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Long getCobnId() {
		return cobnId;
	}

	public void setCobnId(Long cobnId) {
		this.cobnId = cobnId;
	}

	public String getDscDataVencimento() {
		if (alcoDataVencimento != null) {
			dscDataVencimento = new SimpleDateFormat("dd/MM/yyyy").format(alcoDataVencimento);
		}
		return dscDataVencimento;
	}

	public void setDscDataVencimento(String dscDataVencimento) {
		this.dscDataVencimento = dscDataVencimento;
	}

	public ControleCobrancaDTO getControleCobrancaDTO() {
		return controleCobrancaDTO;
	}

	public void setControleCobrancaDTO(ControleCobrancaDTO controleCobrancaDTO) {
		this.controleCobrancaDTO = controleCobrancaDTO;
	}

	public String getDscDataPagamento() {
		if (this.controleCobrancaDTO != null && this.controleCobrancaDTO.getDataPagamento() != null) {
			dscDataPagamento = new SimpleDateFormat("dd/MM/yyyy").format(this.controleCobrancaDTO.getDataPagamento());
		}
		return dscDataPagamento;
	}

	public void setDscDataPagamento(String dscDataPagamento) {
		this.dscDataPagamento = dscDataPagamento;
	}

	@Override
	public String toString() {
		return "AlunoCobranca [codigo=" + codigo + ", alcoDataVencimento=" + alcoDataVencimento + ", alcoMesReferencia="
				+ alcoMesReferencia + ", statusCobranca=" + statusCobranca + ", aluno=" + aluno + ", cobnId=" + cobnId
				+ ", dscDataVencimento=" + dscDataVencimento + ", controleCobrancaDTO=" + controleCobrancaDTO
				+ ", dscDataPagamento=" + dscDataPagamento + "]";
	}
	
}