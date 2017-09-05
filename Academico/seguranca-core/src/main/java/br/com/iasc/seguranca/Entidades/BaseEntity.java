package br.com.iasc.seguranca.Entidades;

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
