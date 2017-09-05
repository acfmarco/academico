package br.com.iasc.seguranca.bo;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.iasc.seguranca.Entidades.UsuarioAcessoErro;
import br.com.iasc.seguranca.dao.UsuarioAcessoErroDAO;
import br.com.iasc.utils.exception.AcademicoException;;

/**
 * Classe de negócio para a classe de domínio Módulo
 *
 * @author  Marco Figueiredo
 * 
 * Copyright notice (c) 2016 BBPrevidência S/A
 */
@Service("usuarioAcessoErroBO")
public class UsuarioAcessoErroBO implements Serializable {

	private static final long serialVersionUID = -1L;

	private static Logger log = Logger.getLogger(UsuarioAcessoErroBO.class);

	private UsuarioAcessoErroDAO usuarioAcessoErroDAO;

	@Autowired
	public void setUsuarioAcessoErroDAO(@Qualifier("usuarioAcessoErroDao") UsuarioAcessoErroDAO usuarioAcessoErroDAO) {
		this.usuarioAcessoErroDAO = usuarioAcessoErroDAO;
		this.usuarioAcessoErroDAO.setPersistentClass(UsuarioAcessoErro.class);
	}

	/**
	 * Método responsável por consultar módulo por Código (id) sequencial da tabela
	 * @author  Marco Figueiredo
	 * @param   Long codigo
	 * @return  {@link UsuarioAcessoErro}
	 * @throws  AcademicoException
	 */
	public UsuarioAcessoErro pesquisarUsuarioAcessoErroCodigo(Long numero) throws AcademicoException {

		try {
			return this.usuarioAcessoErroDAO.pesquisarUsuarioAcessoErroPorCodigo(numero);

		} catch (Exception e) {
			throw new AcademicoException("Nao foi possivel fazer operacao", e);
		}
	}

	/**
	 * Método responsável por salvar um UsuarioAcesso
	 * @author Marco Figueiredo
	 * @param  {@link UsuarioAcessoErro}
	 * @throws  AcademicoException
	 */
	public void salvarUsuarioAcessoErro(UsuarioAcessoErro usuarioAcessoErro) throws AcademicoException {

		try {

			this.usuarioAcessoErroDAO.salvarUsuarioAcessoErro(usuarioAcessoErro);

		} catch (Exception e) {
			log.error(e);
			throw new AcademicoException("Não foi possível realizar está operaçao", e);
		}
	}

	/**
	 * Método responsável por excluir um UsuarioAcesso
	 * @author  Marco Figueiredo
	 * @param  {@link UsuarioAcessoErro}
	 * @throws  AcademicoException
	 */
	public void apagarUsuarioAcessoErro(UsuarioAcessoErro usuarioAcessoErro) throws AcademicoException {

		try {

			this.usuarioAcessoErroDAO.apagarUsuarioAcessoErro(usuarioAcessoErro);

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
	public List<UsuarioAcessoErro> listarTodasUsuarioAcesso() throws AcademicoException {
		try {
			return this.usuarioAcessoErroDAO.listarTodosUsuarioAcessoErros();
			
		} catch (Exception e) {
			log.error(e);
			throw new AcademicoException("Não foi possível realizar está operaçao", e);
		}
	}

}
