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
import br.com.iasc.seguranca.Entidades.ProgramaTipoAcesso;
import br.com.iasc.utils.exception.AcademicoException;

/**
 * Classe de acesso aos dados de Programa
 *
 * @author  Marco Figueiredo
 * @since 28/11/2016
 * 
 *        Copyright notice (c) 2016 BBPrevidência S/A
 */
@Repository
@Qualifier("programaTipoAcessoDao")
@Scope(proxyMode = ScopedProxyMode.NO, value = "prototype")
public class ProgramaTipoAcessoDAO extends JpaDao<ProgramaTipoAcesso> implements Serializable {

	private static final long serialVersionUID = -1L;

	private static Logger log = Logger.getLogger(ProgramaTipoAcesso.class);

	/**
	 * Salva ou altera cadasto o programa tipo acesso correspondente. Salva se o mesmo já contenha
	 * id na sessao do provedor de persistência, ou insere se nao há id
	 * correspondente.
	 * 
	 * @author Marco Figueiredo
	 * @since 22/08/2017
	 * @param {@link ProgramaTipoAcesso}
	 * @throws AcademicoException
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void salvarProgramaTipoAcesso(ProgramaTipoAcesso programaTipoAcesso) throws AcademicoException {

		if (programaTipoAcesso == null) {
			throw new AcademicoException("O Programa tipo acesso deve estar inicializado para inserçao ou alteraçao.");
		} else {

			try {
				if (programaTipoAcesso.getCodigo() != null) {
					update(programaTipoAcesso);
				} else {
					save(programaTipoAcesso);
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
	 * @param {@link ProgramaTipoAcesso}
	 * @throws AcademicoException
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void apagarProgramaTipoAcesso(ProgramaTipoAcesso programaTipoAcesso) throws AcademicoException {

		if (programaTipoAcesso == null) {
			throw new AcademicoException("O módulo deve estar inicializado para inserçao ou alteraçao.");
		} else {

			try {
				delete(programaTipoAcesso.getCodigo());
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
	public ProgramaTipoAcesso pesquisarProgramaTipoAcessoPorCodigo(Long numero) throws AcademicoException {

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
	 * @return  {@link List<ProgramaTipoAcesso>}
	 * @throws  AcademicoException
	 */
	public List<ProgramaTipoAcesso> listarTodosProgramaTipoAcessos() throws AcademicoException {
		try {
			return findAll();
		} catch (Exception ex) {
			log.error(ex);
			throw new AcademicoException("Não foi possível realizar esta operação", ex);
		}

	}

}
