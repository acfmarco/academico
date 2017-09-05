package br.com.iasc.seguranca.testes;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.iasc.seguranca.Entidades.Usuario;
import br.com.iasc.seguranca.bo.TipoAcessoBO;
import br.com.iasc.seguranca.bo.UsuarioBO;
import br.com.iasc.seguranca.testes.base.TesteBase;
import br.com.iasc.seguranca.util.core.Criptografia;

/**
 * Teste unitário para verificação de métodos diversos
 *
 * 
 * Copyright notice (c) 2017 Universidade de Tecnologia S/A
 */
public class TesteUsuario extends TesteBase {

	@Autowired
	private UsuarioBO usuarioBO;
	
	@Autowired
	private TipoAcessoBO tipoAcessoBO;

	@Test
	public void inserirUsuario() {

		try {
			Usuario usuario = new Usuario();
			
			usuario.setUsuaNome("Marco Antonio Figueiredo");
			usuario.setUsuaDatNascimento(new Date());
			usuario.setUsuaEmail("marcosaig@hotmail.com");
			usuario.setUsuaLogin("marco12345");
			usuario.setUsuaSenha(Criptografia.criptografar("123"));
			usuario.setTipoAcesso(this.tipoAcessoBO.pesquisarTipoAcessoCodigo(1L));
			
			this.usuarioBO.salvarUsuario(usuario);
			
			assertTrue(usuario.getCodigo() == null);
			System.out.println("@Test - Usuário não inserido");

		} catch (Exception e) {
			System.out.println("Falhou");
			fail(e.getMessage());
		}

	}
	
	@Test
	public void exlcuirUsuario() {

		try {
			Usuario usuario = new Usuario();
			
			usuario = this.usuarioBO.consultarUsuarioPorCodigo(3L);
			
			this.usuarioBO.apagarUsuario(usuario);
			
			System.out.println("@Test - Usuário não inserido");

		} catch (Exception e) {
			System.out.println("Falhou");
			fail(e.getMessage());
		}

	}
	
	@Test
	public void pesquisarUsuario() {

		try {
			Usuario usuario = new Usuario();
			
			usuario = this.usuarioBO.consultarUsuarioPorCodigo(3L);
			
			assertNotNull(usuario);
			
			System.out.println("@Test - Usuário não encontrado: " + usuario.getUsuaNome());			

		} catch (Exception e) {
			System.out.println("Falhou");
			fail(e.getMessage());
		}

	}
	
	@Test
	public void atualizarUsuario() {

		try {
			Usuario usuario = new Usuario();
			
			//Pesquisa usuário a ser atualizado
			usuario = this.usuarioBO.consultarUsuarioPorCodigo(3L);
			assertNotNull(usuario);
			
			usuario.setUsuaDatTrocaSenha(new Date());
			usuario.setUsuaSenha(Criptografia.criptografar("123"));
			
			System.out.println("@Test - Usuário não encontrado: " + usuario.getUsuaNome());			

		} catch (Exception e) {
			System.out.println("Falhou");
			fail(e.getMessage());
		}

	}

}