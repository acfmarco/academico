package br.com.iasc.academico.Entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="TAB_ALUNO")
public class Aluno implements Serializable, BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="TAB_ALUNO_GENERATOR", sequenceName="S_ALUNO_01", allocationSize= 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TAB_ALUNO_GENERATOR")
	@Column(name="ALUN_ID")
	private Long codigo;
	
	@Column(name="ALUN_CPF")
	private String alunCPF;
	
	@Column(name="ALUN_NOM")
	private String alunNome;
	
	@Column(name="ALUN_DAT_NASC")
	private String alunDataNascimento;
	
	@Column(name="ALUN_END")
	private String alunEndereco;
	
	@Column(name="ALUN_END_NR")
	private String alunEnderecoNumero;
	
	@Column(name="ALUN_END_COMPL")
	private String alunEnderecoComplemento;
	
	@Column(name="ALUN_BAIRRO")
	private String alunBairro;
	
	@Column(name="ESTA_ID")
	private String estado;
	
	@Column(name="PAIS_ID")
	private String pais;
	
	@Column(name="CURS_ID")
	private String curso;
	
	@Transient
	private String matricula;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getAlunCPF() {
		return alunCPF;
	}

	public void setAlunCPF(String alunCPF) {
		this.alunCPF = alunCPF;
	}

	public String getAlunNome() {
		return alunNome;
	}

	public void setAlunNome(String alunNome) {
		this.alunNome = alunNome;
	}

	public String getAlunDataNascimento() {
		return alunDataNascimento;
	}

	public void setAlunDataNascimento(String alunDataNascimento) {
		this.alunDataNascimento = alunDataNascimento;
	}

	public String getAlunEndereco() {
		return alunEndereco;
	}

	public void setAlunEndereco(String alunEndereco) {
		this.alunEndereco = alunEndereco;
	}

	public String getAlunEnderecoNumero() {
		return alunEnderecoNumero;
	}

	public void setAlunEnderecoNumero(String alunEnderecoNumero) {
		this.alunEnderecoNumero = alunEnderecoNumero;
	}

	public String getAlunEnderecoComplemento() {
		return alunEnderecoComplemento;
	}

	public void setAlunEnderecoComplemento(String alunEnderecoComplemento) {
		this.alunEnderecoComplemento = alunEnderecoComplemento;
	}

	public String getAlunBairro() {
		return alunBairro;
	}

	public void setAlunBairro(String alunBairro) {
		this.alunBairro = alunBairro;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
}
