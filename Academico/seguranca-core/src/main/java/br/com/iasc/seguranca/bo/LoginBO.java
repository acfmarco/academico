package br.com.iasc.seguranca.bo;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.iasc.seguranca.Entidades.Usuario;
import br.com.iasc.seguranca.dao.LoginDAO;
import br.com.iasc.utils.exception.AcademicoException;
/**
 * Classe que representação o padrão de projeto
 * Bussines Object - BO, a qual é responsável por 
 * conter as regras negociais do sistema 
 * 
 * @author arquitetura Cobra 
 *
 */

@Service("loginBO")
public class LoginBO implements Serializable {

	private static final long serialVersionUID = -5700430784289908995L;

	private LoginDAO loginDao;

	public LoginBO() {
		super();

	}

	/**
	 * Método responsável por autenticar o usuário
	 * 
	 * @param login
	 * @param senha
	 * @return {@link Usuario} 
	 * @throws AcademicoException
	 */

	public Usuario autenticarUsuario(String login, String senha) throws AcademicoException {
		try {

			return loginDao.autenticarUsuario(login, senha);

		} catch (AcademicoException e) {
			throw new AcademicoException("Não foi possível realizar está operação");
		}
	}

	/**
	 * Método responsável por alterar um  usuário
	 * 
	 * @param usuario
	 * @throws AcademicoException
	 */

	public void alterarUsuario(Usuario usuario) throws AcademicoException {
		try {

			loginDao.alterarUsuario(usuario);

		} catch (AcademicoException e) {
			e.printStackTrace();
			throw new AcademicoException("Não foi possível realizar está operação");
		}
	}

	/**
	 * Método responsável por consultar usuario por código 
	 * 
	 * @param usuario
	 * @return {@link Usuario}
	 * @throws AcademicoException
	 */

	public Usuario consultarUsuarioPorCodigo(Usuario usuario) throws AcademicoException {
		try {

			return loginDao.consultarUsuarioPorCodigo(usuario);

		} catch (AcademicoException e) {
			e.printStackTrace();
			throw new AcademicoException("Não foi possível realizar está operação");
		}
	}

	/**
	 * Método responsável por listar um 
	 * usuário
	 * 
	 * @param usuario
	 * @return {@link Collection}
	 * @throws AcademicoException
	 */

	public Collection<Usuario> listarUsuario() throws AcademicoException {
		try {

			return loginDao.listarUsuario();

		} catch (AcademicoException e) {
			e.printStackTrace();
			throw new AcademicoException("Não foi possível realizar está operação");
		}
	}

	/**
	 * Método responsável por salvar um 
	 * usuário
	 * 
	 * @param usuario
	 * @throws AcademicoException
	 */

	public void removerUsuario(Usuario usuario) throws AcademicoException {
		try {

			loginDao.removerUsuario(usuario);

		} catch (AcademicoException e) {
			e.printStackTrace();
			throw new AcademicoException("Não foi possível realizar está operação");
		}
	}

	/**
	 * Método responsável por salvar um 
	 * usuário
	 * 
	 * @param usuario
	 * @throws AcademicoException
	 */

	public void salvarUsuario(Usuario usuario) throws AcademicoException {
		try {

			loginDao.salvarUsuario(usuario);

		} catch (AcademicoException e) {
			e.printStackTrace();
			throw new AcademicoException("Não foi possível realizar está operação");
		}
	}

	/**
	 * Método para injeção de dependência
	 * da camada de persistência da aplicação
	 * 
	 * @param loginDao
	 */

	@Autowired
	public void setLoginDao(@Qualifier("loginDao") LoginDAO loginDao) {
		this.loginDao = loginDao;
		this.loginDao.setPersistentClass(Usuario.class);
	}

}
