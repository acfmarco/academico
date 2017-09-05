package br.com.iasc.seguranca.Entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SEG_USUARIO_PROGRAMA database table.
 * 
 */
@Entity
@Table(name="SEG_USUARIO_PROGRAMA")
@NamedQuery(name="UsuarioPrograma.findAll", query="SELECT u FROM UsuarioPrograma u")
public class UsuarioPrograma implements Serializable, BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEG_USUARIO_PROGRAMA_GENERATOR", sequenceName="S_USU_PROG_01", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEG_USUARIO_PROGRAMA_GENERATOR")
	@Column(name="UPRO_ID")
	private Long codigo;

	@Column(name="UPRO_IND_CONSULTA")
	private BigDecimal uproIndConsulta;

	@Column(name="UPRO_IND_EDITA")
	private BigDecimal uproIndEdita;

	@Column(name="UPRO_IND_EXCLUI")
	private BigDecimal uproIndExclui;

	@Column(name="UPRO_IND_INSERE")
	private BigDecimal uproIndInsere;

	//bi-directional many-to-one association to Programa
	@ManyToOne
	@JoinColumn(name="PROG_ID")
	private Programa programa;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="USUA_ID")
	private Usuario usuario;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public BigDecimal getUproIndConsulta() {
		return uproIndConsulta;
	}

	public void setUproIndConsulta(BigDecimal uproIndConsulta) {
		this.uproIndConsulta = uproIndConsulta;
	}

	public BigDecimal getUproIndEdita() {
		return uproIndEdita;
	}

	public void setUproIndEdita(BigDecimal uproIndEdita) {
		this.uproIndEdita = uproIndEdita;
	}

	public BigDecimal getUproIndExclui() {
		return uproIndExclui;
	}

	public void setUproIndExclui(BigDecimal uproIndExclui) {
		this.uproIndExclui = uproIndExclui;
	}

	public BigDecimal getUproIndInsere() {
		return uproIndInsere;
	}

	public void setUproIndInsere(BigDecimal uproIndInsere) {
		this.uproIndInsere = uproIndInsere;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}