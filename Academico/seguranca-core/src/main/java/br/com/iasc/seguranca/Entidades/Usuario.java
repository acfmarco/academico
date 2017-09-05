package br.com.iasc.seguranca.Entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the SEG_USUARIO database table.
 * 
 */
@Entity
@Table(name="SEG_USUARIO")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable, BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEG_USUARIO_GENERATOR", sequenceName = "S_USUARIO_01", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEG_USUARIO_GENERATOR")
	@Column(name="USUA_ID")
	private Long codigo;

	@Temporal(TemporalType.DATE)	
	@Column(name="USUA_DAT_NASCIMENTO")
	private Date usuaDatNascimento;

	@Temporal(TemporalType.DATE)
	@Column(name="USUA_DAT_TROCA_SENHA")
	private Date usuaDatTrocaSenha;

	@Column(name="USUA_EMAIL")
	private String usuaEmail;
	
	@Column(name="USUA_LOGIN")
	private String usuaLogin;

	@Column(name="USUA_IND_STS")
	private long usuaIndSts;

	@Column(name="USUA_NOME")
	private String usuaNome;

	@Column(name="USUA_SENHA")
	private String usuaSenha;

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
	
	public Date getUsuaDatNascimento() {
		return usuaDatNascimento;
	}

	public void setUsuaDatNascimento(Date usuaDatNascimento) {
		this.usuaDatNascimento = usuaDatNascimento;
	}

	public Date getUsuaDatTrocaSenha() {
		return usuaDatTrocaSenha;
	}

	public void setUsuaDatTrocaSenha(Date usuaDatTrocaSenha) {
		this.usuaDatTrocaSenha = usuaDatTrocaSenha;
	}

	public String getUsuaEmail() {
		return usuaEmail;
	}

	public void setUsuaEmail(String usuaEmail) {
		this.usuaEmail = usuaEmail;
	}
	
	public long getUsuaIndSts() {
		return usuaIndSts;
	}

	public void setUsuaIndSts(long usuaIndSts) {
		this.usuaIndSts = usuaIndSts;
	}

	public String getUsuaNome() {
		return usuaNome;
	}

	public void setUsuaNome(String usuaNome) {
		this.usuaNome = usuaNome;
	}

	public String getUsuaSenha() {
		return usuaSenha;
	}

	public void setUsuaSenha(String usuaSenha) {
		this.usuaSenha = usuaSenha;
	}

	public TipoAcesso getTipoAcesso() {
		return tipoAcesso;
	}

	public void setTipoAcesso(TipoAcesso tipoAcesso) {
		this.tipoAcesso = tipoAcesso;
	}

	public String getUsuaLogin() {
		return usuaLogin;
	}

	public void setUsuaLogin(String usuaLogin) {
		this.usuaLogin = usuaLogin;
	}
	
}