package br.com.iasc.academico.Entidades;

import java.io.Serializable;

/**
 * Interface principal para a utilização do conversor genérico.
 * 
 * @author tiago.menezes
 */
public interface BaseEntity extends Serializable {

	/**
	 * Chave do objeto de negócio.
	 * 
	 * @return Long
	 */
	public Long getCodigo();

}
