package br.com.iasc.seguranca.dao;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import br.com.iasc.infra.repository.JpaDao;
import br.com.iasc.seguranca.Entidades.Usuario;
import br.com.iasc.utils.exception.AcademicoException;

/**
 * Classe que respresenta o padrão de projeto 
 * Data Access Object - DAO, a qual é responsável por 
 * controlar as operações realizadas com o banco de dados
 * 
 * 
 */

@Repository
@Qualifier("loginDao")
@Scope(proxyMode = ScopedProxyMode.NO, value = "prototype")
public class LoginDAO extends JpaDao<Usuario> implements Serializable {

	public LoginDAO() {
		super();
	}

	private static final long serialVersionUID = 8394150511932238437L;

	/**
	 * Método responsável por autenticar o usuário
	 * 
	 * @param login
	 * @param senha
	 * @return {@link Usuario}
	 * @throws AcademicoException
	 */

	public Usuario autenticarUsuario(String login, String senha) throws AcademicoException {

		Usuario usuario = new Usuario();

		try {

			usuario = findById(senha);

		} catch (AcademicoException e) {
			throw new AcademicoException("Não foi possível realizar está operação");
		}

		return usuario;
	}

	/**
	 * Método responsável por salvar um  usuário
	 * 
	 * @param usuario
	 * @throws AcademicoException
	 */

	public void alterarUsuario(Usuario usuario) throws AcademicoException {
		try {

			update(usuario);

		} catch (AcademicoException e) {
			e.printStackTrace();
			throw new AcademicoException("Não foi possível realizar está operação");
		}
	}

	/**
	 * Método responsável por consultar Usuario por codigo 
	 * 
	 * @param usuario
	 * @throws AcademicoException
	 */

	public Usuario consultarUsuarioPorCodigo(Usuario usuario) throws AcademicoException {
		try {

			return null;

		} catch (AcademicoException e) {
			e.printStackTrace();
			throw new AcademicoException("Não foi possível realizar está operação");
		}
	}

	/**
	 * Método responsável por listar um usuário
	 * 
	 * @return {@link Collection}
	 * @throws AcademicoException
	 */

	public Collection<Usuario> listarUsuario() throws AcademicoException {
		try {

			return findAll();

		} catch (AcademicoException e) {
			e.printStackTrace();
			throw new AcademicoException("Não foi possível realizar está operação");
		}
	}

	/**
	 * Método responsável por remover um  usuário
	 * 
	 * @param usuario
	 * @throws AcademicoException
	 */

	public void removerUsuario(Usuario usuario) throws AcademicoException {
		try {

			delete(usuario);

		} catch (AcademicoException e) {
			e.printStackTrace();
			throw new AcademicoException("Não foi possível realizar está operação");
		}
	}

	/**
	 * Método responsável por salvar um usuário
	 * 
	 * @param usuario
	 * @throws AcademicoException
	 */

	public void salvarUsuario(Usuario usuario) throws AcademicoException {
		try {

			save(usuario);

		} catch (AcademicoException e) {
			e.printStackTrace();
			throw new AcademicoException("Não foi possível realizar está operação");
		}
	}

}