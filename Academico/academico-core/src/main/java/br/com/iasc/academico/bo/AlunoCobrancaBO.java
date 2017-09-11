package br.com.iasc.academico.bo;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.iasc.academico.Entidades.Aluno;
import br.com.iasc.academico.Entidades.AlunoCobranca;
import br.com.iasc.academico.dao.AlunoCobrancaDAO;
import br.com.iasc.academico.dao.AlunoDAO;
import br.com.iasc.utils.exception.AcademicoException;;

/**
 * Classe de negócio para a classe de domínio Módulo
 *
 * @author  Marco Figueiredo
 * 
 * Copyright notice (c) 2016 BBPrevidência S/A
 */
@Service("alunoCobrancaBO")
public class AlunoCobrancaBO implements Serializable {

	private static final long serialVersionUID = -1L;

	private static Logger log = Logger.getLogger(AlunoCobrancaBO.class);

	private AlunoCobrancaDAO alunoCobrancaDAO;

	@Autowired
	public void setAlunoCobrancaDAO(@Qualifier("alunoCobrancaDao") AlunoCobrancaDAO alunoCobrancaDAO) {
		this.alunoCobrancaDAO = alunoCobrancaDAO;
		this.alunoCobrancaDAO.setPersistentClass(AlunoCobranca.class);
	}

	/**
	 * Método responsável por consultar módulo por Código (id) sequencial da tabela
	 * @author  Marco Figueiredo
	 * @param   Long codigo
	 * @return  {@link AlunoCobranca}
	 * @throws  AcademicoException
	 */
	public AlunoCobranca pesquisarAlunoCobrancaCodigo(Long numero) throws AcademicoException {

		try {
			return this.alunoCobrancaDAO.pesquisarAlunoCobrancaPorCodigo(numero);

		} catch (Exception e) {
			throw new AcademicoException("Nao foi possivel fazer operacao", e);
		}
	}
	
	/**
	 * Método responsável por consultar módulo por Aluno (id) sequencial da tabela
	 * @author  Marco Figueiredo
	 * @param   Long codigo
	 * @return  {@link AlunoCobranca}
	 * @throws  AcademicoException
	 */
	public List<AlunoCobranca> pesquisarAlunoCobrancaPorAluno(Aluno aluno) throws AcademicoException {

		try {
			return this.alunoCobrancaDAO.pesquisarAlunoCobrancaPorAluno(aluno);

		} catch (Exception e) {
			throw new AcademicoException("Nao foi possivel fazer operacao", e);
		}
	}
	
	
	/**
	 * Método responsável por salvar um Aluno Cobranca
	 * @author Marco Figueiredo
	 * @param  {@link alunoCobranca}
	 * @throws  AcademicoException
	 */
	public void salvarAlunoCobranca(AlunoCobranca alunoCobranca) throws AcademicoException {

		try {

			this.alunoCobrancaDAO.salvarAlunoCobranca(alunoCobranca);

		} catch (Exception e) {
			log.error(e);
			throw new AcademicoException("Não foi possível realizar está operaçao", e);
		}
	}

	/**
	 * Método responsável por excluir um Aluno Cobranca
	 * @author  Marco Figueiredo
	 * @param  {@link alunoCobranca}
	 * @throws  AcademicoException
	 */
	public void apagarAlunoCobranca(AlunoCobranca alunoCobranca) throws AcademicoException {

		try {

			this.alunoCobrancaDAO.apagarAlunoCobranca(alunoCobranca);

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
	public List<AlunoCobranca> listarTodasCobrancas() throws AcademicoException {
		try {
			return this.alunoCobrancaDAO.listarTodosAlunoCobrancas();
			
		} catch (Exception e) {
			log.error(e);
			throw new AcademicoException("Não foi possível realizar está operaçao", e);
		}
	}

}
