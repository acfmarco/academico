package br.com.iasc.seguranca.bo;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.iasc.seguranca.Entidades.Programa;
import br.com.iasc.seguranca.dao.ProgramaDAO;
import br.com.iasc.utils.exception.AcademicoException;;

/**
 * Classe de negócio para a classe de domínio Módulo
 *
 * @author  Marco Figueiredo
 * 
 * Copyright notice (c) 2016 BBPrevidência S/A
 */
@Service("programaBO")
public class ProgramaBO implements Serializable {

	private static final long serialVersionUID = -1L;

	private static Logger log = Logger.getLogger(ProgramaBO.class);

	private ProgramaDAO programaDAO;

	@Autowired
	public void setProgramaDAO(@Qualifier("programaDao") ProgramaDAO ProgramaDAO) {
		this.programaDAO = ProgramaDAO;
		this.programaDAO.setPersistentClass(Programa.class);
	}

	/**
	 * Método responsável por consultar módulo por Código (id) sequencial da tabela
	 * @author  Marco Figueiredo
	 * @param   Long codigo
	 * @return  {@link Programa}
	 * @throws  AcademicoException
	 */
	public Programa pesquisarProgramaCodigo(Long numero) throws AcademicoException {

		try {
			return this.programaDAO.pesquisarProgramaPorCodigo(numero);

		} catch (Exception e) {
			throw new AcademicoException("Nao foi possivel fazer operacao", e);
		}
	}

	/**
	 * Método responsável por salvar um Programa
	 * @author Marco Figueiredo
	 * @param  {@link Programa}
	 * @throws  AcademicoException
	 */
	public void salvarPrograma(Programa programa) throws AcademicoException {

		try {

			this.programaDAO.salvarPrograma(programa);

		} catch (Exception e) {
			log.error(e);
			throw new AcademicoException("Não foi possível realizar está operaçao", e);
		}
	}

	/**
	 * Método responsável por excluir um Programa
	 * @author  Marco Figueiredo
	 * @param  {@link Programa}
	 * @throws  AcademicoException
	 */
	public void apagarPrograma(Programa programa) throws AcademicoException {

		try {

			this.programaDAO.apagarPrograma(programa);

		} catch (Exception e) {

			log.error(e);
			throw new AcademicoException("Não foi possível realizar está operaçao", e);
		}
	}

	/**
	 * Método responsável por listar todos progamas
	 * @author  Marco Figueiredo
	 * @param  {@link Programa}
	 * @throws  AcademicoException
	 */
	public List<Programa> listarTodasPrograma() throws AcademicoException {
		try {
			return this.programaDAO.listarTodosProgramas();
			
		} catch (Exception e) {
			log.error(e);
			throw new AcademicoException("Não foi possível realizar está operaçao", e);
		}
	}

}
