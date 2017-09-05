package br.com.iasc.seguranca.Entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the SEG_MODULO database table.
 * 
 */
@Entity
@Table(name="SEG_MODULO")
@NamedQuery(name="Modulo.findAll", query="SELECT m FROM Modulo m")
public class Modulo implements Serializable, BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEG_MODULO_GENERATOR", sequenceName="S_MODULO_01", allocationSize= 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEG_MODULO_GENERATOR")
	@Column(name="MODU_ID")
	private Long codigo;

	@Column(name="MODU_CLASS")
	private String moduClass;

	@Column(name="MODU_DSC")
	private String moduDsc;

	@Column(name="MODU_ICONE")
	private String moduIcone;

	@Column(name="MODU_NOM")
	private String moduNom;

	@Column(name="MODU_NUM_ORDEM")
	private BigDecimal moduNumOrdem;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getModuClass() {
		return moduClass;
	}

	public void setModuClass(String moduClass) {
		this.moduClass = moduClass;
	}

	public String getModuDsc() {
		return moduDsc;
	}

	public void setModuDsc(String moduDsc) {
		this.moduDsc = moduDsc;
	}

	public String getModuIcone() {
		return moduIcone;
	}

	public void setModuIcone(String moduIcone) {
		this.moduIcone = moduIcone;
	}

	public String getModuNom() {
		return moduNom;
	}

	public void setModuNom(String moduNom) {
		this.moduNom = moduNom;
	}

	public BigDecimal getModuNumOrdem() {
		return moduNumOrdem;
	}

	public void setModuNumOrdem(BigDecimal moduNumOrdem) {
		this.moduNumOrdem = moduNumOrdem;
	}

}