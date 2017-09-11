package br.com.iasc.academico.Entidades;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import javax.persistence.*;
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

	@Column(name="ALCO_STATUS")
	private Long alcoStatus;

	@ManyToOne
	@JoinColumn(name="ALUN_ID")
	private Aluno aluno;

	@Column(name="COBN_ID")
	private Long cobnId;
	
	@Transient
	private String dscDataVencimento;

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

}