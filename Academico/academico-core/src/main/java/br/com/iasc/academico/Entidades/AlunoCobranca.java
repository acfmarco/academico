package br.com.iasc.academico.Entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
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

	@Temporal(TemporalType.DATE)
	@Column(name="ALCO_DAT_VENC")
	private Date alcoDataVencimento;

	@Column(name="ALCO_MES_REF")
	private long alcoMesReferencia;

	@Column(name="ALCO_STATUS")
	private Long alcoStatus;

	@Column(name="ALUN_ID")
	private Aluno aluno;

	@Column(name="COBN_ID")
	private BigDecimal cobnId;

	public AlunoCobranca() {
	}

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

	public long getAlcoMesReferencia() {
		return alcoMesReferencia;
	}

	public void setAlcoMesReferencia(long alcoMesReferencia) {
		this.alcoMesReferencia = alcoMesReferencia;
	}

	public Long getAlcoStatus() {
		return alcoStatus;
	}

	public void setAlcoStatus(Long alcoStatus) {
		this.alcoStatus = alcoStatus;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public BigDecimal getCobnId() {
		return cobnId;
	}

	public void setCobnId(BigDecimal cobnId) {
		this.cobnId = cobnId;
	}

}