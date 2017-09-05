package br.com.iasc.infra.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import br.com.iasc.infra.repository.dto.Paginacao;

/**
 * Interfaces de infraestrutura de acesso a camada de persistencia.
 *
 * @author Tiago Menezes
 * @since 19/12/2012
 * 
 * Copyright notice (c) 2012 BBPrevidência S/A
 * @param <T> 
 */
public interface Dao<T> extends Serializable {

	EntityManager getEntityManager();

	/**
	 * É obrigatório implementar o método para atribuir o tipo da classe
	 * persistente.
	 * 
	 * @param persistentClass
	 */
	void setPersistentClass(Class<T> persistentClass);

	/**
	 * Cria o objeto no banco.
	 * 
	 * @param obj
	 *            o objeto
	 */
	void save(T obj);

	/**
	 * Salva os registro passados como argumento na lista de objetos.
	 *
	 * @param list
	 */
	void saveAll(List<T> list);

	/**
	 * Se o objeto existir, atualiza o objeto no banco. Se não existir cria-se o
	 * objeto no banco.
	 * 
	 * @param obj o objeto
	 */
	void update(T obj);

	/**
	 * Recupera o total de registros de uma pesquisa dos objeto da classe da
	 * entidade utilizando as propriedades informadas.
	 * 
	 * @return Integer
	 */
	Integer count();

	/**
	 * Apagar um objeto do banco.
	 * 
	 * @param obj o objeto
	 */
	void delete(Serializable id);

	/**
	 * Recuperar todos os registros de uma entidade.
	 * 
	 * @return List<T>
	 */
	List<T> findAll();

	/**
	 * Recuperar um objeto pelo seu identificador.
	 * 
	 * @param id
	 * @return T
	 */
	T findById(Serializable id);

	/**
	 * Pesquisar por uma hql query, os parâmetros são opcionais.
	 * 
	 * @param queryStr
	 * @param params
	 * @return T
	 */
	T findByQueryAndParametros(String queryStr, Object... params);

	/**
	 * Pesquisar por uma hql query, os parâmetros são opcionais.
	 * 
	 * @param queryStr
	 * @param params
	 * @return List<?>
	 */
	List<?> find(String queryStr, Object... params);

	/**
	 * Pesquisar por uma hql query, os parametros e são opcionais e retorna um resultado único.
	 * 
	 * @param queryStr
	 * @param params
	 * @return Object
	 */
	Object findSingleResult(String queryStr, Object... params);

	/**
	 * Pesquisar utilizando uma named query contendo parâmetros nomeados.
	 * 
	 * @param params os parametros da query
	 * @param queryname nome da query
	 * @return lista com os objetos encontrados
	 */
	List<T> findByNamedParams(String queryname, Map<String, Object> params);

	/**
	 * Pesquisar por uma hql query, os parâmetros são nomeados e opcionais.
	 * 
	 * @param queryStr
	 * @param params
	 * @return List<?>
	 */
	List<?> findByNamedParamsHql(String queryStr, Map<String, Object> params);

	/**
	 * Pesquisar por uma sql native query, os parâmetros são nomeados e opcionais.
	 *
	 * @param sql
	 * @param params
	 * @return T
	 */
	T findByNativeQueryParams(String sql, Map<String, Object> params);

	/**
	 * Pesquisar utilizando uma query SQL contendo parâmetros sem nome.
	 * 
	 * @param params os parametros da query
	 * @param queryStr a query SQL
	 * @return List<T> lista com os objetos encontrados
	 */
	List<T> findByNativeQuery(String sql, Object... params);

	/**
	 * Pesquisar utilizando uma native query SQL contendo parâmetros sem nome e
	 * retornando único objeto.
	 * 
	 * @param params os parametros da query
	 * @param queryStr a query SQL
	 * @return T
	 */
	T findByNativeQuerySingleResult(String queryStr, Object... params);

	/**
	 * Pesquisar utilizando uma query SQL contendo parâmetros sem nome com opção
	 * de paginação.
	 * 
	 * @param params os parametros da query
	 * @param queryStr a query SQL
	 * @return List<T> lista com os objetos encontrados.
	 */
	List<T> findByNativeQuery(String sql, Paginacao paginacao, Object... params);

	/**
	 * Pesquisar utilizando uma query JPQL contendo parâmetros sem nome com opção
	 * de paginação.
	 *
	 * @param namedQuery
	 * @param paginacao
	 * @param params
	 * @return List<T>
	 */
	List<T> findByNamedQuery(String namedQuery, Paginacao paginacao, Object... params);

	/**
	 * Pesquisar utilizando uma query JPQL contendo parâmetros sem nome sem opção
	 * de paginação.
	 *
	 * @param namedQuery
	 * @param paginacao
	 * @param params
	 * @return List<T>
	 */
	List<T> findByNamedQuery(String namedQuery, Object... params);

	/**
	 * Pesquisar utilizando uma query 
	 * 
	 * @param jpql
	 * @return List<T>
	 */
	List<T> listByQuery(String jpql);

}