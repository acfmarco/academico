package br.com.iasc.academico.bo;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.iasc.academico.Entidades.Curso;
import br.com.iasc.academico.dao.CursoDAO;
import br.com.iasc.utils.exception.AcademicoException;;

/**
 * Classe de negócio para a classe de domínio Módulo
 *
 * @author  Marco Figueiredo
 * 
 * Copyright notice (c) 2016 BBPrevidência S/A
 */
@Service("cursoBO")
public class CursoBO implements Serializable {

	private static final long serialVersionUID = -1L;

	private static Logger log = Logger.getLogger(Curso.class);

	private CursoDAO cursoDAO;

	@Autowired
	public void setCursoDAO(@Qualifier("cursoDao") CursoDAO cursoDAO) {
		this.cursoDAO = cursoDAO;
		this.cursoDAO.setPersistentClass(Curso.class);
	}

	/**
	 * Método responsável por consultar módulo por Código (id) sequencial da tabela
	 * @author  Marco Figueiredo
	 * @param   Long codigo
	 * @return  {@link Curso}
	 * @throws  AcademicoException
	 */
	public Curso pesquisarCursoCodigo(Long numero) throws AcademicoException {

		try {
			return this.cursoDAO.pesquisarCursoPorCodigo(numero);

		} catch (Exception e) {
			throw new AcademicoException("Nao foi possivel fazer operacao", e);
		}
	}
	
	/**
	 * Método responsável por salvar um Curso
	 * @author Marco Figueiredo
	 * @param  {@link Curso}
	 * @throws  AcademicoException
	 */
	public void salvarCurso(Curso Curso) throws AcademicoException {

		try {

			this.cursoDAO.salvarCurso(Curso);

		} catch (Exception e) {
			log.error(e);
			throw new AcademicoException("Não foi possível realizar está operaçao", e);
		}
	}

	/**
	 * Método responsável por excluir um Curso
	 * @author  Marco Figueiredo
	 * @param  {@link Curso}
	 * @throws  AcademicoException
	 */
	public void apagarCurso(Curso Curso) throws AcademicoException {

		try {

			this.cursoDAO.apagarCurso(Curso);

		} catch (Exception e) {

			log.error(e);
			throw new AcademicoException("Não foi possível realizar está operaçao", e);
		}
	}

	/**
	 * Método responsável por listar todos módulos
	 * @author  Marco Figueiredo
	 * @param  {@link Curso}
	 * @throws  AcademicoException
	 */
	public List<Curso> listarTodasCurso() throws AcademicoException {
		try {
			return this.cursoDAO.listarTodosCursos();
			
		} catch (Exception e) {
			log.error(e);
			throw new AcademicoException("Não foi possível realizar está operaçao", e);
		}
	}

}
