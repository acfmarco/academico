package br.com.iasc.academico.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.iasc.academico.Entidades.Aluno;
import br.com.iasc.academico.Entidades.AlunoCobranca;
import br.com.iasc.infra.repository.JpaDao;
import br.com.iasc.utils.exception.AcademicoException;

/**
 * Classe de acesso aos dados de alunoCobranca
 *
 * @author  Marco Figueiredo
 * @since 05/09/2017
 * 
 *        Copyright notice (c) 2016 BBPrevidência S/A
 */
@Repository
@Qualifier("alunoCobrancaDao")
@Scope(proxyMode = ScopedProxyMode.NO, value = "prototype")
public class AlunoCobrancaDAO extends JpaDao<AlunoCobranca> implements Serializable {

	private static final long serialVersionUID = -1L;

	private static Logger log = Logger.getLogger(AlunoCobrancaDAO.class);

	/**
	 * Salva ou altera cadasto o alunoCobranca correspondente. Salva se o mesmo já contenha
	 * id na sessao do provedor de persistência, ou insere se nao há id
	 * correspondente.
	 * 
	 * @author Marco Figueiredo
	 * @since 22/08/2017
	 * @param {@link alunoCobranca}
	 * @throws AcademicoException
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void salvarAlunoCobranca(AlunoCobranca alunoCobranca) throws AcademicoException {

		if (alunoCobranca == null) {
			throw new AcademicoException("O alunoCobranca deve estar inicializado para inserçao ou alteraçao.");
		} else {

			try {
				if (alunoCobranca.getCodigo() != null) {
					update(alunoCobranca);
				} else {
					save(alunoCobranca);
				}
				getEntityManager().flush();
			} catch (Exception ex) {
				log.error(ex);
				throw new AcademicoException(ex);
			}
		}
	}

	/**
	 * Exclui a alunoCobranca correspondente.
	 * 
	 * @author Marco Figueiredo
	 * @since 22/08/2017
	 * @param {@link alunoCobranca}
	 * @throws AcademicoException
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void apagarAlunoCobranca(AlunoCobranca alunoCobranca) throws AcademicoException {

		if (alunoCobranca == null) {
			throw new AcademicoException("O alunoCobranca deve estar inicializado para inserçao ou alteraçao.");
		} else {

			try {
				delete(alunoCobranca.getCodigo());
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
	public AlunoCobranca pesquisarAlunoCobrancaPorCodigo(Long numero) throws AcademicoException {

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
	 * @return  {@link List<alunoCobranca>}
	 * @throws  AcademicoException
	 */
	@SuppressWarnings("unchecked")
	public List<AlunoCobranca> listarTodosAlunoCobrancas() throws AcademicoException {
		try {
			return getEntityManager().createNamedQuery("alunoCobranca.findAll").getResultList();
		} catch (Exception ex) {
			log.error(ex);
			throw new AcademicoException("Não foi possível realizar esta operação", ex);
		}

	}

	@SuppressWarnings("unchecked")
	public List<AlunoCobranca> pesquisarAlunoCobrancaPorAluno(Aluno aluno) {
		
		try {
			
			StringBuilder sql = new StringBuilder();

			sql.append(" SELECT FUS ");
			sql.append("   from AlunoCobranca FUS ");
			sql.append("  WHERE FUS.aluno = ? ");
			
			
			Query query = getEntityManager().createQuery(sql.toString());
			query.setParameter(1, aluno);
			
			return query.getResultList();

		} catch (Exception e) {
			log.error(e);
			throw new AcademicoException("Nao foi possível realizar está operaçao", e);
		}
	
	}

}
