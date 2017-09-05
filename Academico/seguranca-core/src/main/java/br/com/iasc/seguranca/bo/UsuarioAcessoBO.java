package br.com.iasc.seguranca.bo;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.iasc.seguranca.Entidades.UsuarioAcesso;
import br.com.iasc.seguranca.dao.UsuarioAcessoDAO;
import br.com.iasc.utils.exception.AcademicoException;;

/**
 * Classe de negócio para a classe de domínio Módulo
 *
 * @author  Marco Figueiredo
 * 
 * Copyright notice (c) 2016 BBPrevidência S/A
 */
@Service("usuarioAcessoBO")
public class UsuarioAcessoBO implements Serializable {

	private static final long serialVersionUID = -1L;

	private static Logger log = Logger.getLogger(UsuarioAcessoBO.class);

	private UsuarioAcessoDAO usuarioAcessoDAO;

	@Autowired
	public void setUsuarioAcessoDAO(@Qualifier("usuarioAcessoDao") UsuarioAcessoDAO usuarioAcessoDAO) {
		this.usuarioAcessoDAO = usuarioAcessoDAO;
		this.usuarioAcessoDAO.setPersistentClass(UsuarioAcesso.class);
	}

	/**
	 * Método responsável por consultar módulo por Código (id) sequencial da tabela
	 * @author  Marco Figueiredo
	 * @param   Long codigo
	 * @return  {@link UsuarioAcesso}
	 * @throws  AcademicoException
	 */
	public UsuarioAcesso pesquisarUsuarioAcessoCodigo(Long numero) throws AcademicoException {

		try {
			return this.usuarioAcessoDAO.pesquisarUsuarioAcessoPorCodigo(numero);

		} catch (Exception e) {
			throw new AcademicoException("Nao foi possivel fazer operacao", e);
		}
	}

	/**
	 * Método responsável por salvar um UsuarioAcesso
	 * @author Marco Figueiredo
	 * @param  {@link UsuarioAcesso}
	 * @throws  AcademicoException
	 */
	public void salvarUsuarioAcesso(UsuarioAcesso usuarioAcesso) throws AcademicoException {

		try {

			this.usuarioAcessoDAO.salvarUsuarioAcesso(usuarioAcesso);

		} catch (Exception e) {
			log.error(e);
			throw new AcademicoException("Não foi possível realizar está operaçao", e);
		}
	}

	/**
	 * Método responsável por excluir um UsuarioAcesso
	 * @author  Marco Figueiredo
	 * @param  {@link UsuarioAcesso}
	 * @throws  AcademicoException
	 */
	public void apagarUsuarioAcesso(UsuarioAcesso usuarioAcesso) throws AcademicoException {

		try {

			this.usuarioAcessoDAO.apagarUsuarioAcesso(usuarioAcesso);

		} catch (Exception e) {

			log.error(e);
			throw new AcademicoException("Não foi possível realizar está operaçao", e);
		}
	}

	/**
	 * Método responsável por listar todos progamas
	 * @author  Marco Figueiredo
	 * @param  {@link UsuarioAcesso}
	 * @throws  AcademicoException
	 */
	public List<UsuarioAcesso> listarTodasUsuarioAcesso() throws AcademicoException {
		try {
			return this.usuarioAcessoDAO.listarTodosUsuarioAcessos();
			
		} catch (Exception e) {
			log.error(e);
			throw new AcademicoException("Não foi possível realizar está operaçao", e);
		}
	}

}
