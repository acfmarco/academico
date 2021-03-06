package br.com.iasc.academico.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.iasc.academico.Entidades.Pais;
import br.com.iasc.infra.repository.JpaDao;
import br.com.iasc.utils.exception.AcademicoException;

/**
 * Classe de acesso aos dados de pais
 *
 * @author  Marco Figueiredo
 * @since 05/09/2017
 * 
 *        Copyright notice (c) 2016 BBPrevidência S/A
 */
@Repository
@Qualifier("paisDao")
@Scope(proxyMode = ScopedProxyMode.NO, value = "prototype")
public class PaisDAO extends JpaDao<Pais> implements Serializable {

	private static final long serialVersionUID = -1L;

	private static Logger log = Logger.getLogger(PaisDAO.class);

	/**
	 * Salva ou altera cadasto o pais correspondente. Salva se o mesmo já contenha
	 * id na sessao do provedor de persistência, ou insere se nao há id
	 * correspondente.
	 * 
	 * @author Marco Figueiredo
	 * @since 22/08/2017
	 * @param {@link Aluno}
	 * @throws AcademicoException
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void salvarPais(Pais pais) throws AcademicoException {

		if (pais == null) {
			throw new AcademicoException("O Pais deve estar inicializado para inserçao ou alteraçao.");
		} else {

			try {
				if (pais.getCodigo() != null) {
					update(pais);
				} else {
					save(pais);
				}
				getEntityManager().flush();
			} catch (Exception ex) {
				log.error(ex);
				throw new AcademicoException(ex);
			}
		}
	}

	/**
	 * Exclui a Pais correspondente.
	 * 
	 * @author Marco Figueiredo
	 * @since 22/08/2017
	 * @param {@link Pais}
	 * @throws AcademicoException
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void apagarPais(Pais pais) throws AcademicoException {

		if (pais == null) {
			throw new AcademicoException("O Pais deve estar inicializado para inserçao ou alteraçao.");
		} else {

			try {
				delete(pais.getCodigo());
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
	public Pais pesquisarPaisPorCodigo(Long numero) throws AcademicoException {

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
	 * @return  {@link List<Pais>}
	 * @throws  AcademicoException
	 */
	@SuppressWarnings("unchecked")
	public List<Pais> listarTodosPaises() throws AcademicoException {
		try {
			return getEntityManager().createNamedQuery("Pais.findAll").getResultList();
		} catch (Exception ex) {
			log.error(ex);
			throw new AcademicoException("Não foi possível realizar esta operação", ex);
		}

	}

}
