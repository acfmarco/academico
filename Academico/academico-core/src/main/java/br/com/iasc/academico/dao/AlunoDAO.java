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
import br.com.iasc.infra.repository.JpaDao;
import br.com.iasc.utils.exception.AcademicoException;

/**
 * Classe de acesso aos dados de Aluno
 *
 * @author  Marco Figueiredo
 * @since 05/09/2017
 * 
 *        Copyright notice (c) 2016 BBPrevidência S/A
 */
@Repository
@Qualifier("alunoDao")
@Scope(proxyMode = ScopedProxyMode.NO, value = "prototype")
public class AlunoDAO extends JpaDao<Aluno> implements Serializable {

	private static final long serialVersionUID = -1L;

	private static Logger log = Logger.getLogger(AlunoDAO.class);

	/**
	 * Salva ou altera cadasto o aluno correspondente. Salva se o mesmo já contenha
	 * id na sessao do provedor de persistência, ou insere se nao há id
	 * correspondente.
	 * 
	 * @author Marco Figueiredo
	 * @since 22/08/2017
	 * @param {@link Aluno}
	 * @throws AcademicoException
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void salvarAluno(Aluno aluno) throws AcademicoException {

		if (aluno == null) {
			throw new AcademicoException("O Aluno deve estar inicializado para inserçao ou alteraçao.");
		} else {

			try {
				if (aluno.getCodigo() != null) {
					update(aluno);
				} else {
					save(aluno);
				}
				getEntityManager().flush();
			} catch (Exception ex) {
				log.error(ex);
				throw new AcademicoException(ex);
			}
		}
	}

	/**
	 * Exclui a aluno correspondente.
	 * 
	 * @author Marco Figueiredo
	 * @since 22/08/2017
	 * @param {@link Aluno}
	 * @throws AcademicoException
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void apagarAluno(Aluno aluno) throws AcademicoException {

		if (aluno == null) {
			throw new AcademicoException("O aluno deve estar inicializado para inserçao ou alteraçao.");
		} else {

			try {
				delete(aluno.getCodigo());
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
	public Aluno pesquisarAlunoPorCodigo(Long numero) throws AcademicoException {

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
	 * @return  {@link List<Aluno>}
	 * @throws  AcademicoException
	 */
	@SuppressWarnings("unchecked")
	public List<Aluno> listarTodosAlunos() throws AcademicoException {
		try {
			return getEntityManager().createNamedQuery("Aluno.findAll").getResultList();
		} catch (Exception ex) {
			log.error(ex);
			throw new AcademicoException("Não foi possível realizar esta operação", ex);
		}

	}

	@SuppressWarnings("unchecked")
	public List<Aluno> pesquisarAlunoNomeECpfEMatricula(String nome, String cpf, String matricula) {
		
		try {
			
			StringBuilder sql = new StringBuilder();

			sql.append(" SELECT FUS ");
			sql.append("   from Aluno FUS ");
			sql.append("  WHERE 1 = 1 ");
			
			if (!nome.isEmpty()){
				sql.append("  AND alunNome like " + "'%" + nome + "%'");
			}
			
			if (!cpf.isEmpty()){
				sql.append("  AND alunCPF = " + cpf);
			}
			
			if (!matricula.isEmpty()){
				sql.append("  AND matricula = " + matricula );
			}

			Query query = getEntityManager().createQuery(sql.toString());

			return query.getResultList();

		} catch (Exception e) {
			log.error(e);
			throw new AcademicoException("Nao foi possível realizar está operaçao", e);
		}
	
	}

}
