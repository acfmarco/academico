package br.com.iasc.academico.bo;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.iasc.academico.Entidades.Estado;
import br.com.iasc.academico.dao.EstadoDAO;
import br.com.iasc.utils.exception.AcademicoException;;

/**
 * Classe de negócio para a classe de domínio Módulo
 *
 * @author  Marco Figueiredo
 * 
 * Copyright notice (c) 2016 BBPrevidência S/A
 */
@Service("estadoBO")
public class EstadoBO implements Serializable {

	private static final long serialVersionUID = -1L;

	private static Logger log = Logger.getLogger(Estado.class);

	private EstadoDAO estadoDAO;

	@Autowired
	public void setEstadoDAO(@Qualifier("estadoDao") EstadoDAO estadoDAO) {
		this.estadoDAO = estadoDAO;
		this.estadoDAO.setPersistentClass(Estado.class);
	}

	/**
	 * Método responsável por consultar módulo por Código (id) sequencial da tabela
	 * @author  Marco Figueiredo
	 * @param   Long codigo
	 * @return  {@link Estado}
	 * @throws  AcademicoException
	 */
	public Estado pesquisarEstadoCodigo(Long numero) throws AcademicoException {

		try {
			return this.estadoDAO.pesquisarEstadoPorCodigo(numero);

		} catch (Exception e) {
			throw new AcademicoException("Nao foi possivel fazer operacao", e);
		}
	}
	
	/**
	 * Método responsável por salvar um Estado
	 * @author Marco Figueiredo
	 * @param  {@link Estado}
	 * @throws  AcademicoException
	 */
	public void salvarEstado(Estado estado) throws AcademicoException {

		try {

			this.estadoDAO.salvarEstado(estado);

		} catch (Exception e) {
			log.error(e);
			throw new AcademicoException("Não foi possível realizar está operaçao", e);
		}
	}

	/**
	 * Método responsável por excluir um Estado
	 * @author  Marco Figueiredo
	 * @param  {@link Estado}
	 * @throws  AcademicoException
	 */
	public void apagarEstado(Estado estado) throws AcademicoException {

		try {

			this.estadoDAO.apagarEstado(estado);

		} catch (Exception e) {

			log.error(e);
			throw new AcademicoException("Não foi possível realizar está operaçao", e);
		}
	}

	/**
	 * Método responsável por listar todos módulos
	 * @author  Marco Figueiredo
	 * @param  {@link Estado}
	 * @throws  AcademicoException
	 */
	public List<Estado> listarTodasEstado() throws AcademicoException {
		try {
			return this.estadoDAO.listarTodosEstados();
			
		} catch (Exception e) {
			log.error(e);
			throw new AcademicoException("Não foi possível realizar está operaçao", e);
		}
	}

}
