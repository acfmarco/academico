package br.com.iasc.infra.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import br.com.iasc.infra.repository.dto.Paginacao;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

/**
 * Classe de infraestrutura de acesso a camada de persistencia.
 * 
 * @author Tiago Menezes
 * @author Lourival Júnior
 * @since 19/12/2012
 * 
 *        Copyright notice (c) 2012 BBPrevidência S/A
 */
public class JpaDao<T> implements Dao<T> {

	private static final long serialVersionUID = -66681029005647575L;

	private static final Logger log = Logger.getLogger(JpaDao.class);

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Variavel utilizada para indicar que deve ser feito o flush em operações com o banco de dados. Usado geralmente para testes unitários. Usar a seguinte configuração no xml do spring: <br />
	 * <br />
	 * 
	 * &ltbean name="databaseRole" class="java.lang.String"&gt<br />
	 * &ltconstructor-arg><br />
	 * &ltvalue>root&lt/value&gt<br />
	 * &lt/constructor-arg&gt<br />
	 * &lt/bean&gt
	 */
	@Autowired(required = false)
	@Qualifier("doFlush")
	private String doFlush;

	private Class<T> persistentClass;

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public void setPersistentClass(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	private void verifyFlush() {
		if (this.doFlush != null && this.doFlush.equalsIgnoreCase("true")) {
			this.entityManager.flush();
		}
	}

	@Override
	public void save(T obj) {
		try {
			this.entityManager.persist(obj);
			verifyFlush();
		} catch (OptimisticLockException e) {
			log.error(e);
			throw e;
		} catch (PersistenceException e) {
			log.error(e);
			throw e;
		}
	}

	public void detach(T obj) {
		try {
			this.entityManager.detach(obj);
		} catch (OptimisticLockException e) {
			log.error(e);
			throw e;
		} catch (PersistenceException e) {
			log.error(e);
			throw e;
		}
	}

	@Override
	public void saveAll(List<T> list) {
		try {
			for (T obj : list) {
				this.entityManager.persist(obj);
			}
			verifyFlush();
		} catch (OptimisticLockException e) {
			log.error(e);
			throw e;
		} catch (PersistenceException e) {
			log.error(e);
			throw e;
		}
	}

	/**
	 * Salva em batch uma lista de entidades.
	 * 
	 * @param entities
	 * @param batchSize
	 * @return
	 */
	public List<T> saveInBatches(final Iterable<T> entities, final int batchSize) {
		log.debug("Executando insert em batch. Tamanho do lote: " + batchSize);
		return ImmutableList.copyOf(Iterables.concat(Iterables.transform(Iterables.partition(entities, batchSize), new Function<List<? extends T>, Iterable<? extends T>>() {
			@Override
			public Iterable<? extends T> apply(final List<? extends T> input) {
				List<T> saved = saveBatch(input);
				entityManager.flush();
				return saved;
			}
		})));
	}

	/**
	 * Executa o persist para um bloco de entidades, retornando para ser enviado para o banco de dados.
	 * 
	 * @param entities
	 * @return
	 */
	private List<T> saveBatch(Iterable<? extends T> entities) {
		List<T> result = new ArrayList<T>();
		for (T entity : entities) {
			entityManager.persist(entity);
			result.add(entity);
		}
		return result;
	}

	@Override
	public void update(T obj) {
		try {
			this.entityManager.merge(obj);
			verifyFlush();
		} catch (OptimisticLockException e) {
			log.error(e);
			throw e;
		} catch (PersistenceException e) {
			log.error(e);
			throw e;
		}
	}

	@Override
	public void delete(Serializable id) {
		try {
			this.entityManager.remove(findById(id));
			verifyFlush();
		} catch (OptimisticLockException e) {
			log.error(e);
			throw e;
		} catch (PersistenceException e) {
			log.error(e);
			throw e;
		}
	}

	@Override
	public Integer count() {
		String jpql = "SELECT count(t) FROM " + getPersistentClass() + " t ";
		Long count = (Long) this.entityManager.createQuery(jpql).getSingleResult();
		return count.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		String sql = "SELECT t FROM " + getPersistentClass().getSimpleName() + " t ";
		return this.entityManager.createQuery(sql).getResultList();
	}

	@Override
	public T findById(Serializable id) {
		return this.entityManager.find(persistentClass, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public T findByNativeQuerySingleResult(String queryStr, Object... params) {
		log.debug("Executando a query nativa: " + queryStr);
		Query query = this.entityManager.createNativeQuery(queryStr, persistentClass);
		setQueryParams(query, params);
		return (T) query.getSingleResult();
	}

	@Override
	public List<?> find(String queryStr, Object... params) {
		log.debug("Executando a JPQL query: " + queryStr);
		Query query = this.entityManager.createQuery(queryStr, persistentClass);
		setQueryParams(query, params);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public T findByQueryAndParametros(String queryStr, Object... params) {
		log.debug("Executando a JPQL query: " + queryStr);
		Query query = this.entityManager.createQuery(queryStr, persistentClass);
		setQueryParams(query, params);
		return (T) query.getSingleResult();
	}

	@Override
	@SuppressWarnings( { "unchecked" })
	public List<T> findByNamedParams(String queryname, Map<String, Object> params) {
		log.debug("Executando a named query: " + queryname);
		Query query = this.entityManager.createNamedQuery(queryname);
		setQueryMapParams(params, query);
		return query.getResultList();
	}

	@Override
	public List<?> findByNamedParamsHql(String queryName, Map<String, Object> params) {
		log.debug("Executando a named query: " + queryName);
		Query query = this.entityManager.createNamedQuery(queryName);
		setQueryMapParams(params, query);
		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public T findByNativeQueryParams(String sql, Map<String, Object> params) {
		log.debug("Executando a native query: " + sql);
		Query query = this.entityManager.createNativeQuery(sql, persistentClass);
		setQueryMapParams(params, query);
		return (T) query.getSingleResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findByNativeQuery(String sql, Object... params) {
		log.debug("Executando a native query: " + sql);
		Query query = this.entityManager.createNativeQuery(sql, persistentClass);
		setQueryParams(query, params);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByNamedQuery(String namedQuery, Paginacao paginacao, Object... params) {
		log.debug("Executando a named query: " + namedQuery);
		Query query = this.entityManager.createNamedQuery(namedQuery);
		setQueryParams(query, params);
		paginar(paginacao, query);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByNamedQuery(String namedQuery, Object... params) {
		log.debug("Executando a named query: " + namedQuery);
		Query query = this.entityManager.createNamedQuery(namedQuery);
		setQueryParams(query, params);
		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findByNativeQuery(String sql, Paginacao paginacao, Object... params) {
		log.debug("Executando a native query: " + sql);
		Query query = this.entityManager.createNativeQuery(sql, persistentClass);
		setQueryParams(query, params);
		paginar(paginacao, query);
		return query.getResultList();
	}

	@Override
	public Object findSingleResult(String queryStr, Object... params) {
		log.debug("Executando a native query: " + queryStr);
		Query query = this.entityManager.createNativeQuery(queryStr);
		setQueryParams(query, params);
		return query.getSingleResult();
	}

	@Override
	public List<T> listByQuery(String jpql) {
		log.debug("Executando a JPQL query: " + jpql);
		return this.entityManager.createQuery(jpql, persistentClass).getResultList();
	}

	/**
	 * Atribuir os parâmetros da query.
	 * 
	 * @param query
	 * @param params
	 */
	private void setQueryParams(Query query, Object... params) {
		log.debug("Parametros da query: ");
		if ((params != null) && (params.length > 0)) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i + 1, params[i]);

				log.debug("Parametro " + (i + 1) + ": " + params[i]);
			}
		}
	}

	/**
	 * @return Class<T>
	 */
	public final Class<T> getPersistentClass() {
		if (this.persistentClass == null) {
			throw new RuntimeException("É necessário invocar o método setPersistentClass(Class<T> clazz)");
		}
		return this.persistentClass;
	}

	/**
	 * Atribuir os parâmetros da query.
	 * 
	 * @param params
	 * @param query
	 */
	@SuppressWarnings("rawtypes")
	private void setQueryMapParams(Map<String, Object> params, Query query) {
		log.debug("Parametros da query: ");
		for (Iterator ite = params.keySet().iterator(); ite.hasNext();) {
			String key = (String) ite.next();
			query.setParameter(key, params.get(key));

			log.debug("Parametro " + key + ": " + params.get(key));
		}
	}

	/**
	 * Atribuir o objeto paginação a query.
	 * 
	 * @param paginacao
	 * @param query
	 */
	private void paginar(Paginacao paginacao, Query query) {
		if (paginacao != null) {
			if (paginacao.getPosicao() != null) {
				query.setFirstResult(paginacao.getPosicao());
			}
			if (paginacao.getLimite() != null) {
				query.setMaxResults(paginacao.getLimite());
			}
		}
	}

}