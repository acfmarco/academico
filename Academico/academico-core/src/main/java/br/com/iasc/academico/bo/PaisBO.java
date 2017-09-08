package br.com.iasc.academico.bo;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.iasc.academico.Entidades.Aluno;
import br.com.iasc.academico.Entidades.Pais;
import br.com.iasc.academico.dao.PaisDAO;
import br.com.iasc.utils.exception.AcademicoException;;

/**
 * Classe de negócio para a classe de domínio Módulo
 *
 * @author  Marco Figueiredo
 * 
 * Copyright notice (c) 2016 BBPrevidência S/A
 */
@Service("paisBO")
public class PaisBO implements Serializable {

	private static final long serialVersionUID = -1L;

	private static Logger log = Logger.getLogger(Aluno.class);

	private PaisDAO paisDAO;

	@Autowired
	public void setPaisDAO(@Qualifier("paisDao") PaisDAO paisDAO) {
		this.paisDAO = paisDAO;
		this.paisDAO.setPersistentClass(Pais.class);
	}

	/**
	 * Método responsável por consultar módulo por Código (id) sequencial da tabela
	 * @author  Marco Figueiredo
	 * @param   Long codigo
	 * @return  {@link Pais}
	 * @throws  AcademicoException
	 */
	public Pais pesquisarPaisCodigo(Long numero) throws AcademicoException {

		try {
			return this.paisDAO.pesquisarPaisPorCodigo(numero);

		} catch (Exception e) {
			throw new AcademicoException("Nao foi possivel fazer operacao", e);
		}
	}
	
	/**
	 * Método responsável por salvar um Pais
	 * @author Marco Figueiredo
	 * @param  {@link Pais}
	 * @throws  AcademicoException
	 */
	public void salvarPais(Pais pais) throws AcademicoException {

		try {

			this.paisDAO.salvarPais(pais);

		} catch (Exception e) {
			log.error(e);
			throw new AcademicoException("Não foi possível realizar está operaçao", e);
		}
	}

	/**
	 * Método responsável por excluir um Pais
	 * @author  Marco Figueiredo
	 * @param  {@link Pais}
	 * @throws  AcademicoException
	 */
	public void apagarPais(Pais pais) throws AcademicoException {

		try {

			this.paisDAO.apagarPais(pais);

		} catch (Exception e) {

			log.error(e);
			throw new AcademicoException("Não foi possível realizar está operaçao", e);
		}
	}

	/**
	 * Método responsável por listar todos módulos
	 * @author  Marco Figueiredo
	 * @param  {@link Pais}
	 * @throws  AcademicoException
	 */
	public List<Pais> listarTodasPais() throws AcademicoException {
		try {
			return this.paisDAO.listarTodosPaises();
			
		} catch (Exception e) {
			log.error(e);
			throw new AcademicoException("Não foi possível realizar está operaçao", e);
		}
	}

}
