package br.com.iasc.seguranca.Entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the SEG_PROGRAMA database table.
 * 
 */
@Entity
@Table(name="SEG_PROGRAMA")
@NamedQuery(name="Programa.findAll", query="SELECT p FROM Programa p")
public class Programa implements Serializable, BaseEntity	 {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEG_PROGRAMA_GENERATOR", sequenceName="S_PROGRAMA_01", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEG_PROGRAMA_GENERATOR")
	@Column(name="PROG_ID")
	private Long codigo;

	@Column(name="PROG_DIRETORIO")
	private String progDiretorio;

	@Column(name="PROG_DSC")
	private String progDsc;

	@Column(name="PROG_FORM")
	private String progForm;

	@Column(name="PROG_ICONE")
	private String progIcone;

	@Column(name="PROG_IND_STS")
	private BigDecimal progIndSts;

	@Column(name="PROG_IND_VISIVEL")
	private BigDecimal progIndVisivel;

	@Column(name="PROG_NOM")
	private String progNom;

	@Column(name="PROG_NUM_ORDEM")
	private BigDecimal progNumOrdem;

	//bi-directional many-to-one association to Modulo
	@ManyToOne
	@JoinColumn(name="MODU_ID")
	private Modulo modulo;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getProgDiretorio() {
		return progDiretorio;
	}

	public void setProgDiretorio(String progDiretorio) {
		this.progDiretorio = progDiretorio;
	}

	public String getProgDsc() {
		return progDsc;
	}

	public void setProgDsc(String progDsc) {
		this.progDsc = progDsc;
	}

	public String getProgForm() {
		return progForm;
	}

	public void setProgForm(String progForm) {
		this.progForm = progForm;
	}

	public String getProgIcone() {
		return progIcone;
	}

	public void setProgIcone(String progIcone) {
		this.progIcone = progIcone;
	}

	public BigDecimal getProgIndSts() {
		return progIndSts;
	}

	public void setProgIndSts(BigDecimal progIndSts) {
		this.progIndSts = progIndSts;
	}

	public BigDecimal getProgIndVisivel() {
		return progIndVisivel;
	}

	public void setProgIndVisivel(BigDecimal progIndVisivel) {
		this.progIndVisivel = progIndVisivel;
	}

	public String getProgNom() {
		return progNom;
	}

	public void setProgNom(String progNom) {
		this.progNom = progNom;
	}

	public BigDecimal getProgNumOrdem() {
		return progNumOrdem;
	}

	public void setProgNumOrdem(BigDecimal progNumOrdem) {
		this.progNumOrdem = progNumOrdem;
	}

	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}
	
}