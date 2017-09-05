package br.com.iasc.seguranca.dao;

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
import br.com.iasc.seguranca.Entidades.Modulo;
import br.com.iasc.utils.exception.AcademicoException;

/**
 * Classe de acesso aos dados de Situação Recebedor
 *
 * @author  BBPF0351 - Marco Figueiredo
 * @since 28/11/2016
 * 
 *        Copyright notice (c) 2016 BBPrevidência S/A
 */
@Repository
@Qualifier("moduloDao")
@Scope(proxyMode = ScopedProxyMode.NO, value = "prototype")
public class ModuloDAO extends JpaDao<Modulo> implements Serializable {

	private static final long serialVersionUID = -1L;

	private static Logger log = Logger.getLogger(Modulo.class);

	/**
	 * Salva ou altera cadasto o módulo correspondente. Salva se o mesmo já contenha
	 * id na sessao do provedor de persistência, ou insere se nao há id
	 * correspondente.
	 * 
	 * @author Marco Figueiredo
	 * @since 22/08/2017
	 * @param {@link modulo}
	 * @throws AcademicoException
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void salvarModulo(Modulo modulo) throws AcademicoException {

		if (modulo == null) {
			throw new AcademicoException("O modulo deve estar inicializado para inserçao ou alteraçao.");
		} else {

			try {
				if (modulo.getCodigo() != null) {
					update(modulo);
				} else {
					save(modulo);
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
	 * @param {@link Modulo}
	 * @throws AcademicoException
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void apagarModulo(Modulo modulo) throws AcademicoException {

		if (modulo == null) {
			throw new AcademicoException("O módulo deve estar inicializado para inserçao ou alteraçao.");
		} else {

			try {
				delete(modulo.getCodigo());
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
	public Modulo pesquisarModuloPorCodigo(Long numero) throws AcademicoException {

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
	 * @return  {@link List<Modulo>}
	 * @throws  AcademicoException
	 */
	@SuppressWarnings("unchecked")
	public List<Modulo> listarTodosModulos() throws AcademicoException {
		try {
			return getEntityManager().createNamedQuery("Modulo.findAll").getResultList();
		} catch (Exception ex) {
			log.error(ex);
			throw new AcademicoException("Não foi possível realizar esta operação", ex);
		}

	}

}
