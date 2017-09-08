package br.com.iasc.seguranca.bo;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.iasc.seguranca.Entidades.Usuario;
import br.com.iasc.seguranca.dao.UsuarioDAO;
import br.com.iasc.seguranca.util.core.Criptografia;
import br.com.iasc.utils.exception.AcademicoException;
import br.com.iasc.utils.seguranca.GeradorSenha;

/**
 * Classe que representação o padrão de projeto Bussines Object - BO, a qual é responsável por conter as regras negociais do Usuario
 * 
 * @author tiago.menezes
 */

@Service("usuarioBO")
public class UsuarioBO implements Serializable {

	private static final long serialVersionUID = -3255274456358285883L;

	private UsuarioDAO usuarioDAO;

	/**
	 * Método responsável por fornecer uma lista de Objetos Usuario
	 * 
	 * @return {@link Collection}
	 * @throws AcademicoException
	 */

	public List<Usuario> listarUsuario(int inicio, int quantidade) throws AcademicoException {
		try {

			return this.usuarioDAO.listarUsuario(inicio, quantidade);

		} catch (AcademicoException e) {
			throw new AcademicoException("Não foi possível realizar está operação");
		}
	}

	/**
	 * Método responsável por fornecer um lista de objetos usuario
	 * 
	 * @return {@link Collection}
	 * @throws AcademicoException
	 */

	public List<Usuario> listarTodos() throws AcademicoException {
		try {

			return usuarioDAO.listarTodos();

		} catch (AcademicoException e) {
			throw new AcademicoException("Não foi possível realizar está operação");
		}
	}

	/**
	 * Método responsável por alterar usuario.
	 * 
	 * @return {@link String}
	 */

	public void atualizarUsuario(Usuario usuario) throws AcademicoException {
		try {

			this.usuarioDAO.atualizarUsuario(usuario);

		} catch (AcademicoException e) {
			throw new AcademicoException("Não foi possível realizar está operação");
		}
	}

	/**
	 * Método responsável por alterar a senha do usuário.
	 * 
	 * @author Rogerio
	 * 
	 */
	public void atualizarSenha(Usuario usuario) throws AcademicoException {
		try {

			//this.usuarioDAO.atualizarSenha(usuario);

		} catch (AcademicoException e) {
			throw new AcademicoException("Não foi possível realizar está operação");
		}
	}

	/**
	 * Método responsável por salvar os dados de um usuario
	 * 
	 * @return {@link String}
	 */

	public void salvarUsuario(Usuario usuario) throws AcademicoException {
		try {

			this.usuarioDAO.salvarUsuario(usuario);

		} catch (AcademicoException e) {
			throw new AcademicoException("Não foi possível realizar está operação");
		}
	}
	
	/**
	 * Método responsável por salvar os dados de um usuario
	 * 
	 * @return {@link String}
	 */

	public void apagarUsuario(Usuario usuario) throws AcademicoException {
		try {

			this.usuarioDAO.apagarUsuario(usuario);

		} catch (AcademicoException e) {
			throw new AcademicoException("Não foi possível realizar está operação");
		}
	}

	/**
	 * Método responsável por consultar verificar se o usuario já foi cadastrado
	 * 
	 * @return {@link String}
	 */

	public boolean existeUsuarioPorNome(String nomeUsuario) throws AcademicoException {
		try {

			return this.usuarioDAO.existeUsuarioPorNome(nomeUsuario);

		} catch (AcademicoException e) {
			throw new AcademicoException("Não foi possível realizar está operação");
		}
	}

	/**
	 * Método responsável por autenticar o usuário
	 * 
	 * @param usuário
	 * @return usuário
	 * @throws Exception 
	 */
	public Usuario autenticarUsuario(String login, String senha) throws Exception {

		try {

			senha = Criptografia.criptografar(senha);
			return this.usuarioDAO.autenticarUsuario(login, senha);

		} catch (AcademicoException e) {
			throw new AcademicoException("Não foi possível realizar está operação");
		}

	}

	/**
	 * 
	 * @param codigoUsuario
	 * @return {@link List<Usuario> }
	 * @throws AcademicoException
	 */
	public Usuario consultarUsuarioPorCodigo(Long codigoUsuario) throws AcademicoException {

		try {
			return this.usuarioDAO.consultarUsuarioPorCodigo(codigoUsuario);

		} catch (AcademicoException e) {
			throw new AcademicoException("Não foi possível realizar está operação - consultarUsuarioPorCodigo", e);
		}
	}

	@Autowired
	public void setUsuarioDAO(@Qualifier("usuarioDao") UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
		this.usuarioDAO.setPersistentClass(Usuario.class);
	}

}
