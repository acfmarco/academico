package br.com.iasc.infra.repository.dto;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe para integração com sistema tesouraria - Transferência de dados
 * @author  Marco Figueiredo
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ControleCobrancaDTO  implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlAttribute
    private Long codigo;
	
	@XmlElement
	private Date dataVencimento;
	
	@XmlElement
    private String enderecoCobranca;
    
	@XmlElement
    private String nome;

	@XmlElement
    private Double valorCobranca;
    
	@XmlElement
	private Date dataPagamento;
	
	@XmlElement
	private Double valorJuros;
	
	@XmlElement
	private Double valorMulta;
	
	@XmlElement
	private Double valorCorrecao;
    
	@XmlElement
    private String status;
    
	@XmlElement
    private String mensagem;
    
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getEnderecoCobranca() {
		return enderecoCobranca;
	}

	public void setEnderecoCobranca(String enderecoCobranca) {
		this.enderecoCobranca = enderecoCobranca;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValorCobranca() {
		return valorCobranca;
	}

	public void setValorCobranca(Double valorCobranca) {
		this.valorCobranca = valorCobranca;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	public Double getValorJuros() {
		return valorJuros;
	}

	public void setValorJuros(Double valorJuros) {
		this.valorJuros = valorJuros;
	}

	public Double getValorMulta() {
		return valorMulta;
	}

	public void setValorMulta(Double valorMulta) {
		this.valorMulta = valorMulta;
	}

	public Double getValorCorrecao() {
		return valorCorrecao;
	}

	public void setValorCorrecao(Double valorCorrecao) {
		this.valorCorrecao = valorCorrecao;
	}

	@Override
	public String toString() {
		return "ControleCobrancaDTO [codigo=" + codigo + ", dataVencimento=" + dataVencimento + ", enderecoCobranca="
				+ enderecoCobranca + ", nome=" + nome + ", valorCobranca=" + valorCobranca + ", dataPagamento="
				+ dataPagamento + ", valorJuros=" + valorJuros + ", valorMulta=" + valorMulta + ", valorCorrecao="
				+ valorCorrecao + ", status=" + status + ", mensagem=" + mensagem + "]";
	}

	public ControleCobrancaDTO(Date dataVencimento, String enderecoCobranca, String nome, Double valorCobranca,
			Date dataPagamento, String status, String mensagem) {
		super();
		this.dataVencimento = dataVencimento;
		this.enderecoCobranca = enderecoCobranca;
		this.nome = nome;
		this.valorCobranca = valorCobranca;
		this.dataPagamento = dataPagamento;
		this.status = status;
		this.mensagem = mensagem;
	}

	public ControleCobrancaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}