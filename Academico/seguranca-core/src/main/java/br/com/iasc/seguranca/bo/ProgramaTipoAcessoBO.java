package br.com.iasc.seguranca.bo;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.iasc.seguranca.Entidades.ProgramaTipoAcesso;
import br.com.iasc.seguranca.dao.ProgramaTipoAcessoDAO;
import br.com.iasc.utils.exception.AcademicoException;;

/**
 * Classe de negócio para a classe de domínio Módulo
 *
 * @author  Marco Figueiredo
 * 
 * Copyright notice (c) 2016 BBPrevidência S/A
 */
@Service("programaTipoAcessoBO")
public class ProgramaTipoAcessoBO implements Serializable {

	private static final long serialVersionUID = -1L;

	private static Logger log = Logger.getLogger(ProgramaTipoAcessoBO.class);

	private ProgramaTipoAcessoDAO programaTipoAcessoDAO;

	@Autowired
	public void setProgramaTipoAcessoDAO(@Qualifier("programaTipoAcessoDao") ProgramaTipoAcessoDAO programaTipoAcessoDAO) {
		this.programaTipoAcessoDAO = programaTipoAcessoDAO;
		this.programaTipoAcessoDAO.setPersistentClass(ProgramaTipoAcesso.class);
	}

	/**
	 * Método responsável por consultar módulo por Código (id) sequencial da tabela
	 * @author  Marco Figueiredo
	 * @param   Long codigo
	 * @return  {@link programaTipoAcesso}
	 * @throws  AcademicoException
	 */
	public ProgramaTipoAcesso pesquisarprogramaTipoAcessoCodigo(Long numero) throws AcademicoException {

		try {
			return this.programaTipoAcessoDAO.pesquisarProgramaTipoAcessoPorCodigo(numero);

		} catch (Exception e) {
			throw new AcademicoException("Nao foi possivel fazer operacao", e);
		}
	}

	/**
	 * Método responsável por salvar um programaTipoAcesso
	 * @author Marco Figueiredo
	 * @param  {@link programaTipoAcesso}
	 * @throws  AcademicoException
	 */
	public void salvarprogramaTipoAcesso(ProgramaTipoAcesso programaTipoAcesso) throws AcademicoException {

		try {

			this.programaTipoAcessoDAO.salvarProgramaTipoAcesso(programaTipoAcesso);

		} catch (Exception e) {
			log.error(e);
			throw new AcademicoException("Não foi possível realizar está operaçao", e);
		}
	}

	/**
	 * Método responsável por excluir um programaTipoAcesso
	 * @author  Marco Figueiredo
	 * @param  {@link programaTipoAcesso}
	 * @throws  AcademicoException
	 */
	public void apagarprogramaTipoAcesso(ProgramaTipoAcesso programaTipoAcesso) throws AcademicoException {

		try {

			this.programaTipoAcessoDAO.apagarProgramaTipoAcesso(programaTipoAcesso);

		} catch (Exception e) {

			log.error(e);
			throw new AcademicoException("Não foi possível realizar está operaçao", e);
		}
	}

	/**
	 * Método responsável por listar todos progamas
	 * @author  Marco Figueiredo
	 * @param  {@link programaTipoAcesso}
	 * @throws  AcademicoException
	 */
	public List<ProgramaTipoAcesso> listarTodasprogramaTipoAcesso() throws AcademicoException {
		try {
			return this.programaTipoAcessoDAO.listarTodosProgramaTipoAcessos();
			
		} catch (Exception e) {
			log.error(e);
			throw new AcademicoException("Não foi possível realizar está operaçao", e);
		}
	}

}
