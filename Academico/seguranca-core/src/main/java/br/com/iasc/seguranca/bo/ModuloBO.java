package br.com.iasc.seguranca.bo;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.iasc.seguranca.Entidades.Modulo;
import br.com.iasc.seguranca.dao.ModuloDAO;
import br.com.iasc.utils.exception.AcademicoException;;

/**
 * Classe de negócio para a classe de domínio Módulo
 *
 * @author  Marco Figueiredo
 * 
 * Copyright notice (c) 2016 BBPrevidência S/A
 */
@Service("moduloBO")
public class ModuloBO implements Serializable {

	private static final long serialVersionUID = -1L;

	private static Logger log = Logger.getLogger(ModuloBO.class);

	private ModuloDAO moduloDAO;

	@Autowired
	public void setModuloDAO(@Qualifier("moduloDao") ModuloDAO ModuloDAO) {
		this.moduloDAO = ModuloDAO;
		this.moduloDAO.setPersistentClass(Modulo.class);
	}

	/**
	 * Método responsável por consultar módulo por Código (id) sequencial da tabela
	 * @author  Marco Figueiredo
	 * @param   Long codigo
	 * @return  {@link Modulo}
	 * @throws  AcademicoException
	 */
	public Modulo pesquisarModuloCodigo(Long numero) throws AcademicoException {

		try {
			return this.moduloDAO.pesquisarModuloPorCodigo(numero);

		} catch (Exception e) {
			throw new AcademicoException("Nao foi possivel fazer operacao", e);
		}
	}

	/**
	 * Método responsável por salvar um modulo
	 * @author Marco Figueiredo
	 * @param  {@link Modulo}
	 * @throws  AcademicoException
	 */
	public void salvarModulo(Modulo modulo) throws AcademicoException {

		try {

			this.moduloDAO.salvarModulo(modulo);

		} catch (Exception e) {
			log.error(e);
			throw new AcademicoException("Não foi possível realizar está operaçao", e);
		}
	}

	/**
	 * Método responsável por excluir um Modulo
	 * @author  Marco Figueiredo
	 * @param  {@link Modulo}
	 * @throws  AcademicoException
	 */
	public void apagarModulo(Modulo modulo) throws AcademicoException {

		try {

			this.moduloDAO.apagarModulo(modulo);

		} catch (Exception e) {

			log.error(e);
			throw new AcademicoException("Não foi possível realizar está operaçao", e);
		}
	}

	/**
	 * Método responsável por listar todos módulos
	 * @author  Marco Figueiredo
	 * @param  {@link Modulo}
	 * @throws  AcademicoException
	 */
	public List<Modulo> listarTodasModulo() throws AcademicoException {
		try {
			return this.moduloDAO.listarTodosModulos();
			
		} catch (Exception e) {
			log.error(e);
			throw new AcademicoException("Não foi possível realizar está operaçao", e);
		}
	}

}
