package br.com.iasc.tesouraria.bo;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.iasc.infra.repository.dto.ControleCobrancaDTO;
import br.com.iasc.tesouraria.Dao.ControleCobrancaDAO;
import br.com.iasc.tesouraria.Entidades.ControleCobranca;
import br.com.iasc.utils.exception.AcademicoException;;

/**
 * Classe de negócio para a classe de domínio Módulo
 *
 * @author  Marco Figueiredo
 * 
 * Copyright notice (c) 2016 BBPrevidência S/A
 */
@Service("controleCobrancaBO")
public class ControleCobrancaBO implements Serializable {

	private static final long serialVersionUID = -1L;

	private static Logger log = Logger.getLogger(ControleCobrancaBO.class);

	private ControleCobrancaDAO controleCobrancaDAO;

	@Autowired
	public void setControleCobrancaDAO(@Qualifier("controleCobrancaDao") ControleCobrancaDAO controleCobrancaDAO) {
		this.controleCobrancaDAO = controleCobrancaDAO;
		this.controleCobrancaDAO.setPersistentClass(ControleCobranca.class);
	}

	/**
	 * Método responsável por consultar controle cobrança por Código (id) sequencial da tabela
	 * @author  Marco Figueiredo
	 * @param   Long codigo
	 * @return  {@link controleCobranca}
	 * @throws  AcademicoException
	 */
	public ControleCobranca pesquisarControleCobrancaCodigo(Long numero) throws AcademicoException {

		try {
			return this.controleCobrancaDAO.pesquisarControleCobrancaPorCodigo(numero);

		} catch (Exception e) {
			throw new AcademicoException("Nao foi possivel fazer operacao", e);
		}
	}

	/**
	 * Método responsável por salvar um controleCobranca
	 * @author Marco Figueiredo
	 * @param  {@link controleCobranca}
	 * @throws  AcademicoException
	 */
	public void salvarControleCobranca(ControleCobranca controleCobranca) throws AcademicoException {

		try {

			this.controleCobrancaDAO.salvarControleCobranca(controleCobranca);

		} catch (Exception e) {
			log.error(e);
			throw new AcademicoException("Não foi possível realizar está operaçao", e);
		}
	}

	/**
	 * Método responsável por excluir um controleCobranca
	 * @author  Marco Figueiredo
	 * @param  {@link controleCobranca}
	 * @throws  AcademicoException
	 */
	public void apagarcontroleCobranca(ControleCobranca controleCobranca) throws AcademicoException {

		try {

			this.controleCobrancaDAO.apagarControleCobranca(controleCobranca);

		} catch (Exception e) {

			log.error(e);
			throw new AcademicoException("Não foi possível realizar está operaçao", e);
		}
	}

	/**
	 * Método responsável por listar todas cobranças
	 * @author  Marco Figueiredo
	 * @param  {@link controleCobranca}
	 * @throws  AcademicoException
	 */
	public List<ControleCobranca> listarTodascontroleCobranca() throws AcademicoException {
		try {
			return this.controleCobrancaDAO.listarTodosControleCobrancas();
			
		} catch (Exception e) {
			log.error(e);
			throw new AcademicoException("Não foi possível realizar está operaçao", e);
		}
	}

	public ControleCobrancaDTO converterCobranca(ControleCobranca cobranca){
		ControleCobrancaDTO cobrancaDTO = new ControleCobrancaDTO();
		
		cobrancaDTO.setDataPagamento(cobranca.getDataPagamento());
		cobrancaDTO.setDataVencimento(cobranca.getDataVencimento());
		cobrancaDTO.setEnderecoCobranca(cobranca.getEnderecoCobranca());
		cobrancaDTO.setValorCobranca(cobranca.getValorCobranca());
		
		return cobrancaDTO;
	}
}
