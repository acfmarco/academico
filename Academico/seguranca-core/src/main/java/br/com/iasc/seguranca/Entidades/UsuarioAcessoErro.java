package br.com.iasc.seguranca.Entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the SEG_USUARIO_ACESSO_ERRO database table.
 * 
 */
@Entity
@Table(name="SEG_USUARIO_ACESSO_ERRO")
@NamedQuery(name="UsuarioAcessoErro.findAll", query="SELECT u FROM UsuarioAcessoErro u")
public class UsuarioAcessoErro implements Serializable, BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEG_USUARIO_ACESSO_ERRO_GENERATOR", sequenceName="S_USU_ACESSO_ERRO_01", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEG_USUARIO_ACESSO_ERRO_GENERATOR")
	@Column(name="UAER_ID")
	private Long codigo;

	@Temporal(TemporalType.DATE)
	@Column(name="UAER_DATA")
	private Date uaerData;

	@Column(name="UAER_LOCAL")
	private String uaerLocal;

	@Column(name="UAER_MESSAGE")
	private String uaerMessage;

	@Column(name="UAER_SOURCE")
	private String uaerSource;

	@Column(name="UAER_TRACE")
	private String uaerTrace;

	//bi-directional many-to-one association to UsuarioAcesso
	@ManyToOne
	@JoinColumn(name="UACE_ID")
	private UsuarioAcesso usuarioAcesso;

	//bi-directional many-to-one association to UsuarioAcessoErro
	@ManyToOne
	@JoinColumn(name="UAER_ID_AUTO")
	private UsuarioAcessoErro usuarioAcessoErro;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Date getUaerData() {
		return uaerData;
	}

	public void setUaerData(Date uaerData) {
		this.uaerData = uaerData;
	}

	public String getUaerLocal() {
		return uaerLocal;
	}

	public void setUaerLocal(String uaerLocal) {
		this.uaerLocal = uaerLocal;
	}

	public String getUaerMessage() {
		return uaerMessage;
	}

	public void setUaerMessage(String uaerMessage) {
		this.uaerMessage = uaerMessage;
	}

	public String getUaerSource() {
		return uaerSource;
	}

	public void setUaerSource(String uaerSource) {
		this.uaerSource = uaerSource;
	}

	public String getUaerTrace() {
		return uaerTrace;
	}

	public void setUaerTrace(String uaerTrace) {
		this.uaerTrace = uaerTrace;
	}

	public UsuarioAcesso getUsuarioAcesso() {
		return usuarioAcesso;
	}

	public void setUsuarioAcesso(UsuarioAcesso usuarioAcesso) {
		this.usuarioAcesso = usuarioAcesso;
	}

	public UsuarioAcessoErro getUsuarioAcessoErro() {
		return usuarioAcessoErro;
	}

	public void setUsuarioAcessoErro(UsuarioAcessoErro usuarioAcessoErro) {
		this.usuarioAcessoErro = usuarioAcessoErro;
	}
	
}