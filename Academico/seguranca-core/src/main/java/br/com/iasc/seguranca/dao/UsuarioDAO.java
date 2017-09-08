package br.com.iasc.seguranca.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import br.com.iasc.infra.repository.JpaDao;
import br.com.iasc.seguranca.Entidades.Usuario;
import br.com.iasc.utils.exception.AcademicoException;

/**
 * Classe de persistencia de objetos Usuario.
 * 
 * @author tiago.menezes
 */

@Repository
@Qualifier("usuarioDAO")
@Scope(proxyMode = ScopedProxyMode.NO, value = "prototype")
public class UsuarioDAO extends JpaDao<Usuario> implements Serializable {

	private static final long serialVersionUID = 1246654528194228498L;

	/**
	 * Método responsável por fornecer um lista de objetos usuario
	 * 
	 * @return {@link Collection}
	 * @throws AcademicoException
	 */

	public List<Usuario> listarUsuario(int inicio, int quantidade) throws AcademicoException {

		try {
			return findAll();

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
	@SuppressWarnings("unchecked")
	public List<Usuario> listarTodos() throws AcademicoException {
		try {

			return getEntityManager().createNamedQuery("Usuario.listarTodos").getResultList();

		} catch (AcademicoException e) {
			throw new AcademicoException("Não foi possível realizar está operação");
		}

	}

	/**
	 * Método responsável por consultar usuario por código
	 * 
	 * @return {@link String}
	 */

	public Usuario consultarUsuarioPorCodigo(Long codigoUsuario) throws AcademicoException {
		try {

			return findById(codigoUsuario);

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
			update(usuario);
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
			save(usuario);
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
			delete(usuario);
			getEntityManager().flush();
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
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT usr FROM Usuario usr ");
			sql.append("WHERE usr.nome = '");
			sql.append(nomeUsuario);
			sql.append("'");

			Usuario usuario = (Usuario) this.findSingleResult(sql.toString());

			if (usuario != null) {
				return true;
			} else {
				return false;
			}

		} catch (AcademicoException e) {
			throw new AcademicoException("Não foi possível realizar está operação");
		}
	}
	
	public Usuario autenticarUsuario(String login, String senha) {

		Usuario usuarioResultado = new Usuario();
		
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT u FROM Usuario u ");
		sql.append("WHERE u.usuaLogin = '" + login + "'");
		
		try {

			Query query = getEntityManager().createQuery(sql.toString());
			
			usuarioResultado = (Usuario) query.getSingleResult();
			
			if (!usuarioResultado.getUsuaSenha().equals(senha)){
				new AcademicoException("Senha inválida");
			}
		
			return usuarioResultado;

		}catch (NoResultException ex){
			new AcademicoException("Não encontrado login de usuário");
			return usuarioResultado;
			
		} catch (Exception e) {
			throw new AcademicoException("Erro ao autenticar usuário");
		}
		
	}

}
