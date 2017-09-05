package br.com.iasc.seguranca.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import br.com.iasc.infra.repository.JpaDao;
import br.com.iasc.seguranca.Entidades.Usuario;
import br.com.iasc.seguranca.util.core.Criptografia;
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
	 * Método responsável por verificar a autenticidade do usuário.
	 * 
	 * @param usuario
	 * @return {@link Boolean}
	 * @throws AcademicoException
	 */
	public Usuario verificarUsuario(Usuario usuario) throws AcademicoException {

		Usuario usuarioResultado = new Usuario();
/*
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT usu.NUM_SEQ_USUARIO, pess.END_EMAIL");
		sb.append(" FROM OWN_SAD.USUARIO_SAD usu , v_pessoa pess");
		sb.append(" WHERE usu.dsc_login = '");
		sb.append(usuario.getLogin());
		sb.append("' AND pess.num_cpf = ");
		sb.append(FormatarDados.retirarCaracteresEspeciais(usuario.getCpf()));
		sb.append(" AND pess.dat_nasc = TO_DATE('");
		sb.append(FormatarDados.converterPadraoData(usuario.getDataNascimento()));
		sb.append("', 'dd/MM/yyyy')");
		sb.append(" AND usu.chave_pessoa = pess.chave_pessoa");

		try {

			Query query = getEntityManager().createNativeQuery(sb.toString());

			Iterator<Object> interResultado = query.getResultList().iterator();

			while (interResultado.hasNext()) {

				Object[] objetoResultante = (Object[]) interResultado.next();

				usuarioResultado.setCodigo(new Long(objetoResultante[0].toString()));
				if (objetoResultante[1] != null) {
					usuarioResultado.setEmail(objetoResultante[1].toString());
				}
			}

		} catch (AcademicoException e) {
			throw new AcademicoException("Não foi possível realizar está operação");
		}
*/
		return usuarioResultado;

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
	
	/**
	 * Método que verifica se existe usuário já com o login informado.
	 * 
	 * @param login
	 * @return boolean
	 * @throws AcademicoException
	 */
	public boolean existeUsuarioPorLogin(String login) throws AcademicoException {
		try {

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT COUNT(NUM_SEQ_USUARIO) AS NUM_SEQ_USUARIO ");
			sql.append("FROM   OWN_SAD.USUARIO_SAD ");
			sql.append("WHERE  dsc_login LIKE ? ");

			Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter(1, login);

			String resultado = query.getSingleResult().toString();
			if (Integer.valueOf(resultado) > 0) {
				return true;
			}

		} catch (Exception e) {
			throw new AcademicoException(e);
		}
		return false;
	}

	/**
	 * Método responsável listar por origem simulação
	 * 
	 * @param codigo
	 * @return {@link List}
	 * @throws AcademicoException
	 */

	@SuppressWarnings("unchecked")
	public List<Usuario> listarPorOrigemSimulacao(long codigo) throws AcademicoException {

		List<Usuario> listaResultado = new ArrayList<Usuario>();
		Usuario usuarioResultado;

		StringBuilder sb = new StringBuilder();

		sb.append("SELECT distinct us.num_seq_usuario,  pess.nom_pessoa");
		sb.append(" FROM own_sad.usuario_sad us, own_sim.SIMULACAO_PARTICIPANTE sp , v_pessoa pess");
		sb.append(" WHERE sp.num_seq_origem_simulacao = " + codigo);
		sb.append(" AND sp.num_seq_usuario = us.num_seq_usuario");
		sb.append(" AND us.chave_pessoa = pess.chave_pessoa");

		try {

			Query query = getEntityManager().createNativeQuery(sb.toString());

			Iterator<Object> interResultado = query.getResultList().iterator();

			while (interResultado.hasNext()) {

				usuarioResultado = new Usuario();

				Object[] objetoResultante = (Object[]) interResultado.next();

				usuarioResultado.setCodigo(new Long(objetoResultante[0].toString()));
				//usuarioResultado.setLogin(objetoResultante[1].toString());

				listaResultado.add(usuarioResultado);
			}

			return listaResultado;

		} catch (AcademicoException e) {
			throw new AcademicoException("Não foi possível realizar está operação");
		}
	}

	public Usuario autenticarUsuario(String login, String senha) throws AcademicoException {
		/*
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT  ");
		sql.append("    USU.NUM_SEQ_USUARIO,  ");
		sql.append("    USU.DSC_LOGIN, ");
		sql.append("    USU.DSC_SENHA, ");
		sql.append("    USU.CHAVE_PESSOA, ");
		sql.append("    USU.STATUS, ");
		sql.append("    USU.DAT_CADASTRO, ");
		sql.append("    PES.NOM_PESSOA,  ");
		sql.append("    PES.END_EMAIL ");
		sql.append("FROM OWN_SAD.USUARIO_SAD USU, OWN_SAD.V_PESSOA PES ");
		sql.append("WHERE USU.CHAVE_PESSOA = PES.CHAVE_PESSOA ");
		sql.append("AND USU.NUM_SEQ_TIPO_PESSOA = PES.NUM_SEQ_TIPO_PESSOA ");
		sql.append("AND USU.DSC_LOGIN = ? AND  USU.DSC_SENHA = ?  AND STATUS IN ('A', 'N') ");
*/
		try {
/*
			Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter(1, login);
			query.setParameter(2, senha);
*/
			Usuario usuarioResultado = new Usuario();
			
			if (login.equals("marco") && (Criptografia.descriptografar(senha).equals("123"))){
				usuarioResultado.setCodigo(1L);
				usuarioResultado.setUsuaEmail("marcosaig@hotmail.com");
				usuarioResultado.setUsuaLogin(login);
				usuarioResultado.setUsuaSenha(senha);
			}
			
			return usuarioResultado;

		} catch (Exception e) {
			throw new AcademicoException("Erro ao autenticar usuário");
		}
	}

}
