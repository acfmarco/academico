package br.com.iasc.academico.controle;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;

import javax.xml.bind.Unmarshaller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.iasc.academico.Entidades.Aluno;
import br.com.iasc.academico.Entidades.AlunoCobranca;
import br.com.iasc.academico.Entidades.Curso;
import br.com.iasc.academico.Entidades.Estado;
import br.com.iasc.academico.Entidades.Pais;
import br.com.iasc.academico.bo.AlunoBO;
import br.com.iasc.academico.bo.AlunoCobrancaBO;
import br.com.iasc.academico.bo.CursoBO;
import br.com.iasc.academico.bo.EstadoBO;
import br.com.iasc.academico.bo.PaisBO;
import br.com.iasc.infra.repository.dto.ControleCobrancaDTO;
import br.com.iasc.seguranca.servico.AmbienteServico;
import br.com.iasc.seguranca.util.FacesUtils;
import br.com.iasc.seguranca.util.Mensagens;

@Component("alunoVisao")
@Scope("session")
public class AlunoVisao implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final String PAGINA_INICIAL = "/template/apresentacao.xhtml" + FacesUtils.PARAMETRO_JSF_REDIRECT;
	private static final String FW_ALUNO = "/paginas/pesquisarAluno.xhtml" + FacesUtils.PARAMETRO_JSF_REDIRECT;
	private static final String FW_MANTEM_ALUNO = "/paginas/mantemAluno.xhtml" + FacesUtils.PARAMETRO_JSF_REDIRECT;
	
	@Autowired
	private AlunoBO alunoBO;
	
	@Autowired
	private CursoBO cursoBO;
	
	@Autowired
	private PaisBO paisBO;
	
	@Autowired
	private EstadoBO estadoBO;
	
	@Autowired
	private AlunoCobrancaBO alunoCobrancaBO;
	
	@Autowired
	private AmbienteServico ambienteServico;
	
	private Aluno aluno;
	private List<Aluno> listaAlunos = new ArrayList<Aluno>();
	private List<Curso> listaCurso = new ArrayList<Curso>();
	private List<Pais> listaPais = new ArrayList<Pais>();
	private List<Estado> listaEstado = new ArrayList<Estado>();
	private List<AlunoCobranca> listaAlunoCobranca = new ArrayList<AlunoCobranca>();
	
	private Aluno alunoSelecionado;
	
	private boolean editar = false;
	
	public AlunoVisao() {
		super();
		aluno = new Aluno();		
	}

	public String iniciarPesquisarAluno(){
		
		limparPesquisa();
		
		return FW_ALUNO;
	}
	
	public String pesquisarAlunos(){
		
		if (this.aluno == null){
			setListaAlunos(this.alunoBO.listarTodasAluno());
		}else{
			setListaAlunos(this.alunoBO.pesquisarAlunoNomeECpfEMatricula(aluno.getAlunNome(), aluno.getAlunCPF(), aluno.getMatricula()));
		}
		
		return FW_ALUNO;
	}
	
	public String limparPesquisa(){
		setAluno(new Aluno());
		setListaAlunos(new ArrayList<Aluno>());
		
		return FW_ALUNO;
	}
	
	public String retornarTelaInicial(){		
		return PAGINA_INICIAL;
	}
	
	public String retornarTelaAluno(){		
		return FW_ALUNO;
	}
	
	public String editarAluno(Aluno aluno){	
		
		if (aluno == null){		
			Mensagens.addError("Aluno deve ser selecionado");
			return FW_ALUNO;
		}else{
			this.editar = true;
			setAlunoSelecionado(aluno);
			
			montarCombos();
			
			setListaAlunoCobranca(this.alunoCobrancaBO.pesquisarAlunoCobrancaPorAluno(aluno));
			setListaAlunoCobranca(pesquisarDetalheCobranca(getListaAlunoCobranca()));
			return FW_MANTEM_ALUNO;
		}
		
	}
	
	public String novoAluno(){	
		
		montarCombos();
		
		return FW_MANTEM_ALUNO;
		
	}
	
	public void montarCombos(){
		setListaCurso(this.cursoBO.listarTodasCurso());
		setListaEstado(this.estadoBO.listarTodasEstado());
		setListaPais(this.paisBO.listarTodasPais());	
		
	}
	
	public List<AlunoCobranca> pesquisarDetalheCobranca(List<AlunoCobranca> listaAlunoCobranca) {

		try {
			for (AlunoCobranca alunoCobranca : this.getListaAlunoCobranca()) {

				URL urlCobranca = new URL(
						this.ambienteServico.getTesouraria() + "dispatcher/getCobranca/" + alunoCobranca.getCobnId());

				JAXBContext context = JAXBContext.newInstance(ControleCobrancaDTO.class);
				Unmarshaller unmarshaller = context.createUnmarshaller();
				ControleCobrancaDTO controleCobrancaDTO = (ControleCobrancaDTO) unmarshaller.unmarshal(urlCobranca);
				alunoCobranca.setControleCobrancaDTO(controleCobrancaDTO);
			}
			
		} catch (Exception e) {
			Mensagens.addError("Erro ao gerar cobranças: " + e.getMessage());
		}
		
		return listaAlunoCobranca;

	}
	
	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Aluno> getListaAlunos() {
		return listaAlunos;
	}

	public void setListaAlunos(List<Aluno> listaAlunos) {
		this.listaAlunos = listaAlunos;
	}

	public Aluno getAlunoSelecionado() {
		return alunoSelecionado;
	}

	public void setAlunoSelecionado(Aluno alunoSelecionado) {
		this.alunoSelecionado = alunoSelecionado;
	}

	public List<Curso> getListaCurso() {
		return listaCurso;
	}

	public void setListaCurso(List<Curso> listaCurso) {
		this.listaCurso = listaCurso;
	}

	public List<Pais> getListaPais() {
		return listaPais;
	}

	public void setListaPais(List<Pais> listaPais) {
		this.listaPais = listaPais;
	}

	public List<Estado> getListaEstado() {
		return listaEstado;
	}

	public void setListaEstado(List<Estado> listaEstado) {
		this.listaEstado = listaEstado;
	}

	public List<AlunoCobranca> getListaAlunoCobranca() {
		return listaAlunoCobranca;
	}

	public void setListaAlunoCobranca(List<AlunoCobranca> listaAlunoCobranca) {
		this.listaAlunoCobranca = listaAlunoCobranca;
	}

	public boolean isEditar() {
		return editar;
	}

	public void setEditar(boolean editar) {
		this.editar = editar;
	}
	
}
