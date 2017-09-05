package br.com.iasc.seguranca.bo;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.iasc.seguranca.Entidades.TipoAcesso;
import br.com.iasc.seguranca.dao.TipoAcessoDAO;
import br.com.iasc.utils.exception.AcademicoException;;

/**
 * Classe de negócio para a classe de domínio Módulo
 *
 * @author  Marco Figueiredo
 * 
 * Copyright notice (c) 2016 BBPrevidência S/A
 */
@Service("tipoAcessoBO")
public class TipoAcessoBO implements Serializable {

	private static final long serialVersionUID = -1L;

	private static Logger log = Logger.getLogger(TipoAcessoBO.class);

	private TipoAcessoDAO tipoAcessoDAO;

	@Autowired
	public void setTipoAcessoDAO(@Qualifier("tipoAcessoDao") TipoAcessoDAO tipoAcessoDAO) {
		this.tipoAcessoDAO = tipoAcessoDAO;
		this.tipoAcessoDAO.setPersistentClass(TipoAcesso.class);
	}

	/**
	 * Método responsável por consultar módulo por Código (id) sequencial da tabela
	 * @author  Marco Figueiredo
	 * @param   Long codigo
	 * @return  {@link TipoAcesso}
	 * @throws  AcademicoException
	 */
	public TipoAcesso pesquisarTipoAcessoCodigo(Long numero) throws AcademicoException {

		try {
			return this.tipoAcessoDAO.pesquisarTipoAcessoPorCodigo(numero);

		} catch (Exception e) {
			throw new AcademicoException("Nao foi possivel fazer operacao", e);
		}
	}

	/**
	 * Método responsável por salvar um TipoAcesso
	 * @author Marco Figueiredo
	 * @param  {@link TipoAcesso}
	 * @throws  AcademicoException
	 */
	public void salvarTipoAcesso(TipoAcesso TipoAcesso) throws AcademicoException {

		try {

			this.tipoAcessoDAO.salvarTipoAcesso(TipoAcesso);

		} catch (Exception e) {
			log.error(e);
			throw new AcademicoException("Não foi possível realizar está operaçao", e);
		}
	}

	/**
	 * Método responsável por excluir um TipoAcesso
	 * @author  Marco Figueiredo
	 * @param  {@link TipoAcesso}
	 * @throws  AcademicoException
	 */
	public void apagarTipoAcesso(TipoAcesso TipoAcesso) throws AcademicoException {

		try {

			this.tipoAcessoDAO.apagarTipoAcesso(TipoAcesso);

		} catch (Exception e) {

			log.error(e);
			throw new AcademicoException("Não foi possível realizar está operaçao", e);
		}
	}

	/**
	 * Método responsável por listar todos progamas
	 * @author  Marco Figueiredo
	 * @param  {@link TipoAcesso}
	 * @throws  AcademicoException
	 */
	public List<TipoAcesso> listarTodasTipoAcesso() throws AcademicoException {
		try {
			return this.tipoAcessoDAO.listarTodosTipoAcessos();
			
		} catch (Exception e) {
			log.error(e);
			throw new AcademicoException("Não foi possível realizar está operaçao", e);
		}
	}

}
