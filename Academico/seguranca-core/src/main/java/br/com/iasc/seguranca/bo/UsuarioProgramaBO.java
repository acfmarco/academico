package br.com.iasc.seguranca.bo;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.iasc.seguranca.Entidades.UsuarioPrograma;
import br.com.iasc.seguranca.dao.UsuarioProgramaDAO;
import br.com.iasc.utils.exception.AcademicoException;;

/**
 * Classe de negócio para a classe de domínio Módulo
 *
 * @author  Marco Figueiredo
 * 
 * Copyright notice (c) 2016 BBPrevidência S/A
 */
@Service("usuarioProgramaBO")
public class UsuarioProgramaBO implements Serializable {

	private static final long serialVersionUID = -1L;

	private static Logger log = Logger.getLogger(UsuarioProgramaBO.class);

	private UsuarioProgramaDAO usuarioProgramaDAO;

	@Autowired
	public void setUsuarioProgramaDAO(@Qualifier("usuarioProgramaDao") UsuarioProgramaDAO usuarioProgramaDAO) {
		this.usuarioProgramaDAO = usuarioProgramaDAO;
		this.usuarioProgramaDAO.setPersistentClass(UsuarioPrograma.class);
	}

	/**
	 * Método responsável por consultar módulo por Código (id) sequencial da tabela
	 * @author  Marco Figueiredo
	 * @param   Long codigo
	 * @return  {@link UsuarioPrograma}
	 * @throws  AcademicoException
	 */
	public UsuarioPrograma pesquisarUsuarioProgramaCodigo(Long numero) throws AcademicoException {

		try {
			return this.usuarioProgramaDAO.pesquisarUsuarioProgramaPorCodigo(numero);

		} catch (Exception e) {
			throw new AcademicoException("Nao foi possivel fazer operacao", e);
		}
	}

	/**
	 * Método responsável por salvar um UsuarioPrograma
	 * @author Marco Figueiredo
	 * @param  {@link UsuarioPrograma}
	 * @throws  AcademicoException
	 */
	public void salvarUsuarioPrograma(UsuarioPrograma usuarioPrograma) throws AcademicoException {

		try {

			this.usuarioProgramaDAO.salvarUsuarioPrograma(usuarioPrograma);

		} catch (Exception e) {
			log.error(e);
			throw new AcademicoException("Não foi possível realizar está operaçao", e);
		}
	}

	/**
	 * Método responsável por excluir um UsuarioPrograma
	 * @author  Marco Figueiredo
	 * @param  {@link UsuarioPrograma}
	 * @throws  AcademicoException
	 */
	public void apagarUsuarioPrograma(UsuarioPrograma usuarioPrograma) throws AcademicoException {

		try {

			this.usuarioProgramaDAO.apagarUsuarioPrograma(usuarioPrograma);

		} catch (Exception e) {

			log.error(e);
			throw new AcademicoException("Não foi possível realizar está operaçao", e);
		}
	}

	/**
	 * Método responsável por listar todos progamas
	 * @author  Marco Figueiredo
	 * @return  {@link List<UsuarioPrograma>}
	 * @throws  AcademicoException
	 */
	public List<UsuarioPrograma> listarTodasUsuarioAcesso() throws AcademicoException {
		try {
			return this.usuarioProgramaDAO.listarTodosUsuarioProgramas();
			
		} catch (Exception e) {
			log.error(e);
			throw new AcademicoException("Não foi possível realizar está operaçao", e);
		}
	}

}
