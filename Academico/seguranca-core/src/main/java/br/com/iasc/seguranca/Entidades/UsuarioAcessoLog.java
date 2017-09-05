package br.com.iasc.seguranca.Entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the SEG_USUARIO_ACESSO_LOG database table.
 * 
 */
@Entity
@Table(name="SEG_USUARIO_ACESSO_LOG")
@NamedQuery(name="UsuarioAcessoLog.findAll", query="SELECT u FROM UsuarioAcessoLog u")
public class UsuarioAcessoLog implements Serializable, BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEG_USUARIO_ACESSO_LOG_GENERATOR", sequenceName="S_USU_ACESSO_LOG_01", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEG_USUARIO_ACESSO_LOG_GENERATOR")
	@Column(name="UALO_ID")
	private Long codigo;

	@Temporal(TemporalType.DATE)
	@Column(name="UALO_DAT")
	private Date ualoDat;

	//bi-directional many-to-one association to Programa
	@ManyToOne
	@JoinColumn(name="PROG_ID")
	private Programa programa;

	//bi-directional many-to-one association to UsuarioAcesso
	@ManyToOne
	@JoinColumn(name="UACE_ID")
	private UsuarioAcesso usuarioAcesso;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Date getUaloDat() {
		return ualoDat;
	}

	public void setUaloDat(Date ualoDat) {
		this.ualoDat = ualoDat;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public UsuarioAcesso getUsuarioAcesso() {
		return usuarioAcesso;
	}

	public void setUsuarioAcesso(UsuarioAcesso usuarioAcesso) {
		this.usuarioAcesso = usuarioAcesso;
	}

}