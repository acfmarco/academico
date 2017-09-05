package br.com.iasc.tesouraria.Dao;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.iasc.infra.repository.JpaDao;
import br.com.iasc.tesouraria.Entidades.ControleCobranca;
import br.com.iasc.utils.exception.AcademicoException;

/**
 * Classe de acesso aos dados de Controle de Cobrança
 *
 * @author  Marco Figueiredo
 * @since 29/08/2017
 * 
 *        Copyright notice (c) 2016 BBPrevidência S/A
 */
@Repository
@Qualifier("controleCobrancaDao")
@Scope(proxyMode = ScopedProxyMode.NO, value = "prototype")
public class ControleCobrancaDAO extends JpaDao<ControleCobranca> implements Serializable {

	private static final long serialVersionUID = -1L;

	private static Logger log = Logger.getLogger(ControleCobranca.class);

	/**
	 * Salva ou altera cadasto o controle de cobrança correspondente. Salva se o mesmo já contenha
	 * id na sessao do provedor de persistência, ou insere se nao há id
	 * correspondente.
	 * 
	 * @author Marco Figueiredo
	 * @since 22/08/2017
	 * @param {@link ControleCobranca}
	 * @throws AcademicoException
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void salvarControleCobranca(ControleCobranca controleCobranca) throws AcademicoException {

		if (controleCobranca == null) {
			throw new AcademicoException("O ControleCobranca deve estar inicializado para inserçao ou alteraçao.");
		} else {

			try {
				if (controleCobranca.getCodigo() != null) {
					update(controleCobranca);
				} else {
					save(controleCobranca);
				}
				getEntityManager().flush();
			} catch (Exception ex) {
				log.error(ex);
				throw new AcademicoException(ex);
			}
		}
	}

	/**
	 * Exclui a módulo correspondente.
	 * 
	 * @author Marco Figueiredo
	 * @since 22/08/2017
	 * @param {@link ControleCobranca}
	 * @throws AcademicoException
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void apagarControleCobranca(ControleCobranca controleCobranca) throws AcademicoException {

		if (controleCobranca == null) {
			throw new AcademicoException("O controle de cobrança deve estar inicializado para inserçao ou alteraçao.");
		} else {

			try {
				delete(controleCobranca.getCodigo());
				getEntityManager().flush();
			} catch (Exception ex) {
				log.error(ex);
				throw new AcademicoException(ex);
			}
		}
	}

	/**
	 * Metodo responsável por pesquisar módulo por código
	 * 
	 * @author Marco Figueiredo
	 * @since 22/08/2017
	 * @param Long
	 * @return long
	 * @throws AcademicoException
	 */
	public ControleCobranca pesquisarControleCobrancaPorCodigo(Long numero) throws AcademicoException {

		try {
			return findById(numero);

		} catch (Exception e) {
			log.error(e);
			throw new AcademicoException("Nao foi possível realizar está operaçao", e);
		}

	}

	/**
	 * Método responsável por listar toda.
	 * 
	 * @author  Marco Figueiredo
	 * @since   22/08/2017
	 * @return  {@link List<ControleCobranca>}
	 * @throws  AcademicoException
	 */
	public List<ControleCobranca> listarTodosControleCobrancas() throws AcademicoException {
		try {
			return findAll();
		} catch (Exception ex) {
			log.error(ex);
			throw new AcademicoException("Não foi possível realizar esta operação", ex);
		}

	}

}
