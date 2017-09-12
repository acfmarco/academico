/**
 * ControleCobranca.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.iasc.tesouraria.Entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.iasc.infra.repository.dto.ControleCobrancaDTO;
import br.com.iasc.seguranca.Entidades.BaseEntity;

/**
 * Classe para integração com sistema tesouraria - Transferência de dados
 * @author  Marco Figueiredo
 *
 */
@Entity
@Table(name="CFT_CONTROLE_COBRANCA")
public class ControleCobranca  implements Serializable, BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CFT_CONTROLE_COBRANCA_GENERATOR", sequenceName="S_COBRANCA_01", allocationSize= 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CFT_CONTROLE_COBRANCA_GENERATOR")
	@Column(name="COBN_ID")
	private Long codigo;
	
	@Column(name="COBN_DAT_VENC")
	private Date dataVencimento;

	@Column(name="COBN_END_COB")
    private String enderecoCobranca;

	@Column(name="COBN_NOM")
	private String nomeCobrado;

    @Column(name="COBN_VAL_COB")
    private Double valorCobranca;
    
    @Column(name="COBN_DAT_PAG")
    private Date dataPagamento;
    
    @Column(name="COBN_JUROS")
    private double valorJuros;
    
    @Column(name="COBN_MULTA")
    private double valorMulta;
    
    @Column(name="COBN_CORRECAO")
    private double valorCorrecao;
    
	public ControleCobranca() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ControleCobranca(ControleCobrancaDTO cobrancaDTO) {
		super();
		this.dataVencimento = cobrancaDTO.getDataVencimento();
		this.enderecoCobranca = cobrancaDTO.getEnderecoCobranca();
		this.valorCobranca = cobrancaDTO.getValorCobranca();
		this.dataPagamento = cobrancaDTO.getDataPagamento();
		this.nomeCobrado = cobrancaDTO.getNome();
	}

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
	
	public String getNomeCobrado() {
		return nomeCobrado;
	}

	public void setNomeCobrado(String nomeCobrado) {
		this.nomeCobrado = nomeCobrado;
	}

	public Double getValorCobranca() {
		return valorCobranca;
	}

	public void setValorCobranca(Double valorCobranca) {
		this.valorCobranca = valorCobranca;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public double getValorJuros() {
		return valorJuros;
	}

	public void setValorJuros(double valorJuros) {
		this.valorJuros = valorJuros;
	}

	public double getValorMulta() {
		return valorMulta;
	}

	public void setValorMulta(double valorMulta) {
		this.valorMulta = valorMulta;
	}

	public double getValorCorrecao() {
		return valorCorrecao;
	}

	public void setValorCorrecao(double valorCorrecao) {
		this.valorCorrecao = valorCorrecao;
	}

	@Override
	public String toString() {
		return "ControleCobranca [codigo=" + codigo + ", dataVencimento=" + dataVencimento + ", enderecoCobranca="
				+ enderecoCobranca + ", nomeCobrado=" + nomeCobrado + ", valorCobranca=" + valorCobranca
				+ ", dataPagamento=" + dataPagamento + ", valorJuros=" + valorJuros + ", valorMulta=" + valorMulta
				+ ", valorCorrecao=" + valorCorrecao + "]";
	} 	
	
}