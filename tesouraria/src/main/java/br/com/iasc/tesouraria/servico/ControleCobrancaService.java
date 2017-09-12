package br.com.iasc.tesouraria.servico;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import br.com.iasc.infra.repository.dto.ControleCobrancaDTO;
import br.com.iasc.tesouraria.Entidades.ControleCobranca;
import br.com.iasc.tesouraria.bo.ControleCobrancaBO;
import br.com.iasc.utils.UtilJava;
import br.com.iasc.utils.exception.AcademicoException;

/**
 * Essa classe vai expor os nossos métodos para serem acessasdos via http
 * 
 * @RestController - Caminho para a chamada da classe que vai representar o nosso serviço
 * */
@RestController
@EnableWebMvc
public class ControleCobrancaService {
 
	@Autowired
	private ControleCobrancaBO controleCobrancaBO;
	
	/*
	 * @Consumes - determina o formato dos dados que vamos postar
	 * @Produces - determina o formato dos dados que vamos retornar
	 * 
	 * Esse método cadastra uma nova pessoa
	 * */
	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST,
    produces = {"application/xml"}, consumes={"application/xml"})
	public ResponseEntity<ControleCobrancaDTO> cadastrar(@RequestBody ControleCobrancaDTO controleCobrancaDTO){
 
		ControleCobranca controleCobranca = new ControleCobranca(controleCobrancaDTO);
 
		try {
 
			this.controleCobrancaBO.salvarControleCobranca(controleCobranca);
			
			controleCobrancaDTO.setMensagem("Registro Inserido com sucesso.");
			return new ResponseEntity<ControleCobrancaDTO>(controleCobrancaDTO, HttpStatus.OK);
			
		}catch (AcademicoException ex){
			controleCobrancaDTO.setStatus("N");
			controleCobrancaDTO.setMensagem("Erro ao tentar inserir Cobrança. " + ex.getMessage());
			return new ResponseEntity<ControleCobrancaDTO>(controleCobrancaDTO, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			controleCobrancaDTO.setStatus("N");
			controleCobrancaDTO.setMensagem("Erro ao tentar inserir Cobrança. Erro não tratado, favor entrar em contato com administradores");
			return new ResponseEntity<ControleCobrancaDTO>(controleCobrancaDTO, HttpStatus.NOT_FOUND);
		}
 
	}
 
	/**
	 * Essse método altera uma pessoa já cadastrada
	 * **/
	@RequestMapping(value = "/alterar", method = RequestMethod.PUT,
    produces = {"application/xml"})
	public ResponseEntity<ControleCobrancaDTO> alterar(@RequestBody ControleCobrancaDTO controleCobrancaDTO){
 
		ControleCobranca controleCobranca = new ControleCobranca(controleCobrancaDTO);
		 
		try {
 
			this.controleCobrancaBO.salvarControleCobranca(controleCobranca);
			
			controleCobrancaDTO.setMensagem("Registro Atualizado com sucesso.");
			return new ResponseEntity<ControleCobrancaDTO>(controleCobrancaDTO, HttpStatus.OK);
			
		}catch (AcademicoException ex){
			controleCobrancaDTO.setStatus("N");
			controleCobrancaDTO.setMensagem("Erro ao tentar atualizar Cobrança" + ex.getMessage());
			return new ResponseEntity<ControleCobrancaDTO>(controleCobrancaDTO, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			controleCobrancaDTO.setStatus("N");
			controleCobrancaDTO.setMensagem("Erro ao tentar atualizar Cobrança. Erro não tratado, favor entrar em contato com administradores");
			return new ResponseEntity<ControleCobrancaDTO>(controleCobrancaDTO, HttpStatus.NOT_FOUND);
		}
 
	}
	/**
	 * Esse método lista todas cobranças cadastradas na base
	 * */
	@RequestMapping(value = "/todasCobrancas", method = RequestMethod.GET,
		    produces = {"application/xml"})
	public ResponseEntity<List<ControleCobrancaDTO>> todasCobrancas(){
 
		try{
			
			List<ControleCobrancaDTO> listaControleCobrancaDTO = new ArrayList<ControleCobrancaDTO>();

			List<ControleCobranca> listaControleCobranca = this.controleCobrancaBO.listarTodascontroleCobranca();
			
			if (UtilJava.isColecaoDiferenteDeVazia(listaControleCobranca)) {
				for (ControleCobranca controleCobranca : listaControleCobranca) {

					listaControleCobrancaDTO.add(this.controleCobrancaBO.converterCobranca(controleCobranca));
				}

				return new ResponseEntity<List<ControleCobrancaDTO>>(listaControleCobrancaDTO, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<ControleCobrancaDTO>>(HttpStatus.NOT_FOUND);
			}
		}catch (Exception e){
			return new ResponseEntity<List<ControleCobrancaDTO>>(HttpStatus.NOT_FOUND);
		}
	}
 
	/**
	 * Esse método busca uma cobrança cadastrada pelo código
	 * */
	@RequestMapping(value = "/getCobranca/{codigo}", method = RequestMethod.GET,
            produces = {"application/xml"})
	public ResponseEntity<ControleCobrancaDTO> getCobranca(@PathVariable("codigo")  String codigo){
 
		ControleCobrancaDTO controleCobrancaDTO = new ControleCobrancaDTO();
		
		try{
			
			ControleCobranca cobranca = new ControleCobranca();
		
			cobranca = this.controleCobrancaBO.pesquisarControleCobrancaCodigo(Long.parseLong(codigo));
			
			if (cobranca == null){
				controleCobrancaDTO.setMensagem("Não encontrada cobrança descrita");
			}else{
				controleCobrancaDTO = this.controleCobrancaBO.converterCobranca(cobranca);
			}
			
			return new ResponseEntity<ControleCobrancaDTO>(controleCobrancaDTO, HttpStatus.OK);
			
		}catch (Exception e){
			controleCobrancaDTO.setStatus("N");
			controleCobrancaDTO.setMensagem("Erro ao pesquisar cobrança descrita");
			return new ResponseEntity<ControleCobrancaDTO>(controleCobrancaDTO, HttpStatus.NOT_FOUND);
		}
	}
 
	/**
	 * Excluindo uma pessoa pelo código
	 * */
	@RequestMapping(value = "/excluir/{codigo}", method = RequestMethod.DELETE,
    produces = {"application/xml"})
	public ResponseEntity<ControleCobrancaDTO> excluir(@PathVariable("codigo") String codigo){
		
		ControleCobrancaDTO cobrancaDTO = new ControleCobrancaDTO();
		
		try {
			
			ControleCobranca cobranca = new ControleCobranca();
			cobranca = this.controleCobrancaBO.pesquisarControleCobrancaCodigo(Long.parseLong(codigo));
			
			cobrancaDTO = this.controleCobrancaBO.converterCobranca(cobranca);
			
			this.controleCobrancaBO.apagarcontroleCobranca(cobranca);
			
			cobrancaDTO.setStatus("S");
			cobrancaDTO.setMensagem("Registro excluido com sucesso!");
			
			return new ResponseEntity<ControleCobrancaDTO>(cobrancaDTO, HttpStatus.OK);
 
		} catch (Exception e) {
			cobrancaDTO.setStatus("N");
			cobrancaDTO.setMensagem("Erro ao excluir o registro! " + e.getMessage());
			return new ResponseEntity<ControleCobrancaDTO>(cobrancaDTO, HttpStatus.NOT_MODIFIED);
		}
 
	}
 
}