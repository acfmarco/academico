package br.com.iasc.academico.bo;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.iasc.academico.Entidades.Aluno;
import br.com.iasc.academico.dao.AlunoDAO;
import br.com.iasc.utils.exception.AcademicoException;;

/**
 * Classe de negócio para a classe de domínio Módulo
 *
 * @author  Marco Figueiredo
 * 
 * Copyright notice (c) 2016 BBPrevidência S/A
 */
@Service("AlunoBO")
public class AlunoBO implements Serializable {

	private static final long serialVersionUID = -1L;

	private static Logger log = Logger.getLogger(Aluno.class);

	private AlunoDAO alunoDAO;

	@Autowired
	public void setAlunoDAO(@Qualifier("AlunoDao") AlunoDAO alunoDAO) {
		this.alunoDAO = alunoDAO;
		this.alunoDAO.setPersistentClass(Aluno.class);
	}

	/**
	 * Método responsável por consultar módulo por Código (id) sequencial da tabela
	 * @author  Marco Figueiredo
	 * @param   Long codigo
	 * @return  {@link Aluno}
	 * @throws  AcademicoException
	 */
	public Aluno pesquisarAlunoCodigo(Long numero) throws AcademicoException {

		try {
			return this.alunoDAO.pesquisarAlunoPorCodigo(numero);

		} catch (Exception e) {
			throw new AcademicoException("Nao foi possivel fazer operacao", e);
		}
	}
	
	/**
	 *
	 * Método responsável por consultar módulo por Código (id) sequencial da tabela
	 * @author  Marco Figueiredo
	 * @param   Long codigo
	 * @return  {@link Aluno}
	 * @throws  AcademicoException
	 */
	public List<Aluno> pesquisarAlunoNomeECpfEMatricula(String nome, String cpf, String matricula) throws AcademicoException {

		try {
			return this.alunoDAO.pesquisarAlunoNomeECpfEMatricula(nome, cpf, matricula);

		} catch (Exception e) {
			throw new AcademicoException("Nao foi possivel fazer operacao", e);
		}
	}

	/**
	 * Método responsável por salvar um Aluno
	 * @author Marco Figueiredo
	 * @param  {@link Aluno}
	 * @throws  AcademicoException
	 */
	public void salvarAluno(Aluno aluno) throws AcademicoException {

		try {

			this.alunoDAO.salvarAluno(aluno);

		} catch (Exception e) {
			log.error(e);
			throw new AcademicoException("Não foi possível realizar está operaçao", e);
		}
	}

	/**
	 * Método responsável por excluir um Aluno
	 * @author  Marco Figueiredo
	 * @param  {@link Aluno}
	 * @throws  AcademicoException
	 */
	public void apagarAluno(Aluno aluno) throws AcademicoException {

		try {

			this.alunoDAO.apagarAluno(aluno);

		} catch (Exception e) {

			log.error(e);
			throw new AcademicoException("Não foi possível realizar está operaçao", e);
		}
	}

	/**
	 * Método responsável por listar todos módulos
	 * @author  Marco Figueiredo
	 * @param  {@link Aluno}
	 * @throws  AcademicoException
	 */
	public List<Aluno> listarTodasAluno() throws AcademicoException {
		try {
			return this.alunoDAO.listarTodosAlunos();
			
		} catch (Exception e) {
			log.error(e);
			throw new AcademicoException("Não foi possível realizar está operaçao", e);
		}
	}

}
