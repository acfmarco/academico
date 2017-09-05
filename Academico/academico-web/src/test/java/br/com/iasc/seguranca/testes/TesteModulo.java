package br.com.iasc.seguranca.testes;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.iasc.seguranca.Entidades.Modulo;
import br.com.iasc.seguranca.bo.ModuloBO;
import br.com.iasc.seguranca.testes.base.TesteBase;

/**
 * Teste unitário para verificação de métodos diversos
 *
 * 
 * Copyright notice (c) 2017 Universidade de Tecnologia S/A
 */
public class TesteModulo extends TesteBase {

	@Autowired
	private ModuloBO moduloBO;
	
	private Modulo modulo;
	
	@Test
	public void inserirModulo() {

		try {
			this.modulo = new Modulo();
			
			this.modulo.setModuNom("Modulo Teste");
			this.modulo.setModuNumOrdem(new BigDecimal(1L));
			this.modulo.setModuDsc("Modulo Teste");
			this.modulo.setModuClass("Teste");
			
			this.moduloBO.salvarModulo(this.modulo);
			
			assertTrue(this.modulo.getCodigo() == null);
			System.out.println("@Test - Módulo não inserido");

		} catch (Exception e) {
			System.out.println("Falhou");
			fail(e.getMessage());
		}

	}
	
	@Test
	public void pesquisarUsuario() {

		try {
			this.modulo = new Modulo();
			
			this.modulo = this.moduloBO.pesquisarModuloCodigo(2L);
			
			assertNotNull(this.modulo);
			
			System.out.println("@Test - Módulo encontrado: " + this.modulo.getModuNom());			

		} catch (Exception e) {
			System.out.println("Falhou");
			fail(e.getMessage());
		}

	}
	
	@Test
	public void atualizarUsuario() {

		try {
			this.modulo = new Modulo();
			
			this.modulo = this.moduloBO.pesquisarModuloCodigo(2L);
			assertNotNull(this.modulo);
			
			this.modulo.setModuNom("Modulo Teste Atualizado");
			this.moduloBO.salvarModulo(this.modulo);
			
			System.out.println("@Test - Módulo Atualizado: " + this.modulo.getModuNom());			

		} catch (Exception e) {
			System.out.println("Falhou");
			fail(e.getMessage());
		}

	}
	
	@Test
	public void exlcuirModulo() {

		try {
			modulo = new Modulo();
			
			modulo = this.moduloBO.pesquisarModuloCodigo(2L);
			
			this.moduloBO.apagarModulo(this.modulo);
			
			this.modulo = this.moduloBO.pesquisarModuloCodigo(2L);
			assertNotNull(this.modulo);
			
			System.out.println("@Test - Módulo excluído");

		} catch (Exception e) {
			System.out.println("Falhou");
			fail(e.getMessage());
		}

	}

}