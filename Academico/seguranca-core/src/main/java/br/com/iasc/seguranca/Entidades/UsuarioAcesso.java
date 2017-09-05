package br.com.iasc.seguranca.Entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the SEG_USUARIO_ACESSO database table.
 * 
 */
@Entity
@Table(name="SEG_USUARIO_ACESSO")
@NamedQuery(name="UsuarioAcesso.findAll", query="SELECT u FROM UsuarioAcesso u")
public class UsuarioAcesso implements Serializable, BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEG_USUARIO_ACESSO_GENERATOR", sequenceName="S_USU_ACESSO_01", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEG_USUARIO_ACESSO_GENERATOR")
	@Column(name="UACE_ID")
	private Long codigo;

	@Temporal(TemporalType.DATE)
	@Column(name="UACE_DAT")
	private Date uaceDat;

	@Column(name="UACE_IP")
	private String uaceIp;

	@Column(name="UACE_NAVEGADOR")
	private String uaceNavegador;

	@Column(name="UACE_NAVEGADOR_VERSAO")
	private String uaceNavegadorVersao;

	@Column(name="UACE_SESSAO")
	private String uaceSessao;

	@Column(name="UACE_SO")
	private String uaceSo;

	//bi-directional many-to-one association to Usuario1
	@ManyToOne
	@JoinColumn(name="USUA_ID")
	private Usuario usuario;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Date getUaceDat() {
		return uaceDat;
	}

	public void setUaceDat(Date uaceDat) {
		this.uaceDat = uaceDat;
	}

	public String getUaceIp() {
		return uaceIp;
	}

	public void setUaceIp(String uaceIp) {
		this.uaceIp = uaceIp;
	}

	public String getUaceNavegador() {
		return uaceNavegador;
	}

	public void setUaceNavegador(String uaceNavegador) {
		this.uaceNavegador = uaceNavegador;
	}

	public String getUaceNavegadorVersao() {
		return uaceNavegadorVersao;
	}

	public void setUaceNavegadorVersao(String uaceNavegadorVersao) {
		this.uaceNavegadorVersao = uaceNavegadorVersao;
	}

	public String getUaceSessao() {
		return uaceSessao;
	}

	public void setUaceSessao(String uaceSessao) {
		this.uaceSessao = uaceSessao;
	}

	public String getUaceSo() {
		return uaceSo;
	}

	public void setUaceSo(String uaceSo) {
		this.uaceSo = uaceSo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}