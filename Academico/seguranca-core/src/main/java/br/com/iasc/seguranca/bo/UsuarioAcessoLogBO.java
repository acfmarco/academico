package br.com.iasc.seguranca.bo;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.iasc.seguranca.Entidades.UsuarioAcessoLog;
import br.com.iasc.seguranca.dao.UsuarioAcessoLogDAO;
import br.com.iasc.utils.exception.AcademicoException;;

/**
 * Classe de negócio para a classe de domínio Módulo
 *
 * @author  Marco Figueiredo
 * 
 * Copyright notice (c) 2016 BBPrevidência S/A
 */
@Service("usuarioAcessoLogBO")
public class UsuarioAcessoLogBO implements Serializable {

	private static final long serialVersionUID = -1L;

	private static Logger log = Logger.getLogger(UsuarioAcessoLogBO.class);

	private UsuarioAcessoLogDAO usuarioAcessoLogDAO;

	@Autowired
	public void setUsuarioAcessoLogDAO(@Qualifier("usuarioAcessoLogDao") UsuarioAcessoLogDAO usuarioAcessoLogDAO) {
		this.usuarioAcessoLogDAO = usuarioAcessoLogDAO;
		this.usuarioAcessoLogDAO.setPersistentClass(UsuarioAcessoLog.class);
	}

	/**
	 * Método responsável por consultar módulo por Código (id) sequencial da tabela
	 * @author  Marco Figueiredo
	 * @param   Long codigo
	 * @return  {@link UsuarioAcessoLog}
	 * @throws  AcademicoException
	 */
	public UsuarioAcessoLog pesquisarUsuarioAcessoLogCodigo(Long numero) throws AcademicoException {

		try {
			return this.usuarioAcessoLogDAO.pesquisarUsuarioAcessoLogPorCodigo(numero);

		} catch (Exception e) {
			throw new AcademicoException("Nao foi possivel fazer operacao", e);
		}
	}

	/**
	 * Método responsável por salvar um UsuarioAcesso
	 * @author Marco Figueiredo
	 * @param  {@link UsuarioAcessoLog}
	 * @throws  AcademicoException
	 */
	public void salvarUsuarioAcessoLog(UsuarioAcessoLog usuarioAcessoLog) throws AcademicoException {

		try {

			this.usuarioAcessoLogDAO.salvarUsuarioAcessoLog(usuarioAcessoLog);

		} catch (Exception e) {
			log.error(e);
			throw new AcademicoException("Não foi possível realizar está operaçao", e);
		}
	}

	/**
	 * Método responsável por excluir um UsuarioAcesso
	 * @author  Marco Figueiredo
	 * @param  {@link UsuarioAcessoLog}
	 * @throws  AcademicoException
	 */
	public void apagarUsuarioAcessoLog(UsuarioAcessoLog usuarioAcessoLog) throws AcademicoException {

		try {

			this.usuarioAcessoLogDAO.apagarUsuarioAcessoLog(usuarioAcessoLog);

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
	public List<UsuarioAcessoLog> listarTodasUsuarioAcesso() throws AcademicoException {
		try {
			return this.usuarioAcessoLogDAO.listarTodosUsuarioAcessoLogs();
			
		} catch (Exception e) {
			log.error(e);
			throw new AcademicoException("Não foi possível realizar está operaçao", e);
		}
	}

}
