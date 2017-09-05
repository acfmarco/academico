package br.com.iasc.utils.reflexao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import br.com.iasc.utils.UtilJava;
import br.com.iasc.utils.exception.InfraException;
import br.com.iasc.utils.formatacao.FormatarDados;

/**
 * <p>Essa classe é utilitária para chamadas dinâmicas. Já contém breve implementação para chamadas dos método get e set de um modelo qualquer.</p>
 * <p>Com essa classe utilitária também é possível verificar diferenças entre classes de mesmo domínio. Ela é acoplada à especificação JPA</p>
 * 
 * @author andre.mello
 *
 */
public final class JavaReflectionInvocador {

	public static final String SET = "set";
	public static final String GET = "get";

	/**
	 * Coloca em letra maiúscula a primeira letra da stringCaptalizacao
	 * 
	 * @author andre.mello
	 * @param stringCaptalizacao a ser captalizada.
	 * @return retorna a stringCaptalizacao com a primeira letra maiúscula.
	 */
	private static String captalizarPrimeiraLetra(String stringCaptalizacao) {

		Character captalizada = Character.toUpperCase(stringCaptalizacao.charAt(0));

		return captalizada.toString().concat(stringCaptalizacao.substring(1));

	}

	/**
	 * Cria nome identificador de método set.
	 * 
	 * @author andre.mello
	 * @param stringFormatarNomeMetodo a ter o fragmento set adicionado e captalização a ser corrigida.
	 * @return a string com captalização e o fragmento set concatenado no início.
	 */
	private static String formatarNomeMetodoSet(String stringFormatarNomeMetodo) {

		return SET.concat(captalizarPrimeiraLetra(stringFormatarNomeMetodo));

	}

	/**
	 * Cria nome identificador de método get.
	 * 
	 * @author andre.mello
	 * @param stringFormatarNomeMetodo a ter o fragmento get adicionado e captalização a ser corrigida.
	 * @return a string com captalização e o fragmento get concatenado no início.
	 */
	private static String formatarNomeMetodoGet(String stringFormatarNomeMetodo) {

		return GET.concat(captalizarPrimeiraLetra(stringFormatarNomeMetodo));

	}

	/**
	 * Invoca um método de acordo com o method passado.
	 * 
	 * @author andre.mello
	 * @param objetoInvocacao o objeto a ter o nomeMetodo invocado.
	 * @para method a ser invacado.
	 * @param argumentos presentes na assinatura de um método.
	 * @return o objeto de retorno da invocação do método.
	 * @throws InfraException tradução das exceptions lançadas no corpo do método.
	 */
	@SuppressWarnings("unchecked")
	public static <T, K> K invocarMetodoReflection(T objetoInvocacao, Method method, Object... argumentos) throws InfraException {

		K retorno = null;

		try {

			retorno = (K) method.invoke(objetoInvocacao, argumentos);

		} catch (IllegalArgumentException e) {

			throw new InfraException(FormatarDados.montarMensagem("Os argumentos passados não são válidos com a invocação do método {0}.", method.getName()));

		} catch (SecurityException e) {

			throw new InfraException(FormatarDados.montarMensagem("Não há acesso para o método invocado ({0}).", method.getName()));

		} catch (IllegalAccessException e) {

			throw new InfraException(FormatarDados.montarMensagem("Não há visibilidade para o método invocado ({0}).", method.getName()));

		} catch (InvocationTargetException e) {

			throw new InfraException(FormatarDados.montarMensagem("O método invocado lançou uma exceção!", method.getName()), e.getTargetException());

		}

		return retorno;

	}

	/**
	 * Invoca o método get nomePropriedade
	 * 
	 * @author andre.mello
	 * @param propriedade nome da propriedade a ter a invocação do método get realizada.
	 * @param objetoInvocacao o objeto a ter o metodo get invocado.
	 * @return o objeto de retorno da invocação do método.
	 * @throws InfraException tradução das exceptions lançadas no corpo do método.
	 */
	@SuppressWarnings("unchecked")
	public static <T, K> K invocarMetodoGet(Field propriedade, T objetoInvocacao) throws InfraException {

		return (K) invocarMetodoReflection(objetoInvocacao, acessarMetodoReflection(formatarNomeMetodoGet(propriedade.getName()), objetoInvocacao));

	}

	/**
	 * Invoca o método get nomePropriedade
	 * 
	 * @author andre.mello
	 * @param propriedade nome da propriedade a ter a invocação do método get realizada.
	 * @param objetoInvocacao o objeto a ter o metodo get invocado.
	 * @return o objeto de retorno da invocação do método.
	 * @throws InfraException tradução das exceptions lançadas no corpo do método.
	 */
	@SuppressWarnings("unchecked")
	public static <T, K> K invocarMetodoGet(String propriedade, T objetoInvocacao) throws InfraException {

		return (K) invocarMetodoReflection(objetoInvocacao, acessarMetodoReflection(formatarNomeMetodoGet(propriedade), objetoInvocacao));

	}

	/**
	 * Invoca o método set nomePropriedade
	 * 
	 * @author andre.mello
	 * @param propriedade nome da propriedade a ter a invocação do método set realizada.
	 * @param objetoInvocacao o objeto a ter o metodo set invocado.
	 * @param argumentos presentes na assinatura de um método.
	 * @return o objeto de retorno da invocação do método.
	 * @throws InfraException tradução das exceptions lançadas no corpo do método.
	 */
	@SuppressWarnings("unchecked")
	public static <T, K> K invocarMetodoSet(Field propriedade, T objetoInvocacao, Object... argumentos) throws InfraException {

		return (K) invocarMetodoReflection(objetoInvocacao, acessarMetodoReflection(formatarNomeMetodoSet(propriedade.getName()), objetoInvocacao, propriedade.getType()), argumentos);

	}

	/**
	 * Invoca o método set de propriedade
	 * 
	 * @author andre.mello
	 * @param propriedade nome da propriedade a ter a invocação do método set realizada.
	 * @param objetoInvocacao o objeto a ter o metodo set invocado.
	 * @param argumentos presentes na assinatura de um método.
	 * @return o objeto de retorno da invocação do método.
	 * @throws InfraException tradução das exceptions lançadas no corpo do método.
	 */
	@SuppressWarnings("unchecked")
	public static <T, K> K invocarMetodoSet(String propriedade, T objetoInvocacao, Object... argumentos) throws InfraException {

		Field propriedadeInvocada = null;
		try {

			propriedadeInvocada = objetoInvocacao.getClass().getDeclaredField(propriedade);

		} catch (SecurityException e) {

			throw new InfraException("Não há permissão para acesso ao método {0}.".replace("{0}", formatarNomeMetodoSet(propriedade)));

		} catch (NoSuchFieldException e) {

			throw new InfraException("O método {0} invocado não existe.".replace("{0}", formatarNomeMetodoSet(propriedade)));

		}

		return (K) invocarMetodoSet(propriedadeInvocada, objetoInvocacao, argumentos);

	}

	/**
	 * Acessa o método nomeMetodo.
	 * 
	 * @author andre.mello
	 * @param nomeMetodo nome do método a ser acessado.
	 * @param objetoInvocacao o objeto que terá o método acessado.
	 * @param tipoArgumentos os tipos dos objetos a serem passados como argumento
	 * @return o método a ser invocado.
	 * @throws InfraException tradução das exceptions lançadas no corpo do método.
	 */
	public static <T, U> Method acessarMetodoReflection(String nomeMetodo, T objetoInvocacao, Class<U>... tipoArgumentos) throws InfraException {

		try {

			if (objetoInvocacao != null)
				return objetoInvocacao.getClass().getMethod(nomeMetodo, tipoArgumentos);
			else
				throw new InfraException("O valor objetoInvocacao não pode ser nulo. Nulo enquanto chamava o método {0}".replace("{0}", nomeMetodo));

		} catch (SecurityException e) {

			throw new InfraException("Não há acesso para o método invocado ({0}).".replace("{0}", nomeMetodo));

		} catch (NoSuchMethodException e) {

			throw new InfraException("O método invocado ({0}) não existe para a instância passada como argumento.".replace("{0}", nomeMetodo));

		}

	}

	/**
	 * Acessa o método nomeMetodo.
	 * 
	 * @author daniel.pacheco
	 * @param nomeMetodo nome do método a ser acessado.
	 * @param classeMetodo a classe que terá o método acessado.
	 * @param tipoArgumentos os tipos dos objetos a serem passados como argumento
	 * @return o método a ser invocado.
	 * @throws InfraException tradução das exceptions lançadas no corpo do método.
	 */
	public static <T, U> Method acessarMetodoReflection(String nomeMetodo, String classeMetodo, Class<U>... tipoArgumentos) throws InfraException {

		Class<?> cls = null;
		try {
			cls = Class.forName(classeMetodo);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			throw new InfraException("A classe invocada ({0}) não existe.".replace("{0}", classeMetodo));
		}

		try {

			if (cls != null)
				return cls.getMethod(nomeMetodo, tipoArgumentos);
			else
				throw new InfraException("O valor objetoInvocacao não pode ser nulo. Nulo enquanto chamava o método {0}".replace("{0}", nomeMetodo));

		} catch (SecurityException e) {

			throw new InfraException("Não há acesso para o método invocado ({0}).".replace("{0}", nomeMetodo));

		} catch (NoSuchMethodException e) {

			throw new InfraException("O método invocado ({0}) não existe para a instância passada como argumento.".replace("{0}", nomeMetodo));

		}

	}

	/**
	 * Métodos responsável por verificar as alterações correspondentes aos relacionamentos e correspondentes às propriedades wrappers de uma entidade.
	 * Lembrando que as alterações percebidas nos relacionamentos não implicam aquelas propriedades da entidade estrangeira, somente a alteração correspondente
	 * ao relacionamento propriamente dito.
	 * 
	 * @author andre.mello
	 * @param objetoNovo novo utilizado para comparação com o objetoBancoDados.
	 * @param objetoAntigo existente em banco de dados que terá seus dados verificados.
	 * @param clazz é a intância da classe de objetoNovo e objetoAntigo
	 * @return true, se não foi alterado; false, se foi alterado.
	 * @throws InfraException tradução das exceptions lançadas no corpo do método.
	 */
	public static <T> Boolean verificarAlteracaoDadosSimplesEEntidadesComposicao(T objetoNovo, T objetoAntigo, Class<T> clazz) throws InfraException {

		Field[] atributosClasseT = null;
		Boolean isIgual = true;

		atributosClasseT = clazz.getDeclaredFields();

		for (int x = 0; x < atributosClasseT.length; x++) {

			isIgual = isPropriedadeIgual(atributosClasseT[x], objetoNovo, objetoAntigo, clazz);

			if (!isIgual)
				break;

		}

		return isIgual;

	}

	/**
	 * Verifica se objetoNovo é igual objetoAntigo.
	 * 
	 * @author andre.mello
	 * @param atributoObjeto que possívelmente foi alterado.
	 * @param objetoNovo objeto alterado com os valores novos.
	 * @param objetoAntigo objeto para comparação com os valores antigos.
	 * @param clazz  é a intância da classe de objetoNovo e objetoBancoDados
	 * @return true, se não foi alterado; false, se foi alterado.
	 * @throws InfraException 
	 */
	public static <T> Boolean isPropriedadeIgual(Field atributoObjeto, T objetoNovo, T objetoAntigo, Class<T> clazz) throws InfraException {

		Boolean isIgual = true;

		if (atributoObjeto.getAnnotation(Column.class) != null) {

			if (JavaReflectionInvocador.invocarMetodoGet(atributoObjeto, objetoNovo) != null) {

				if (atributoObjeto.getType().isPrimitive()) {

					if (JavaReflectionInvocador.invocarMetodoGet(atributoObjeto, objetoNovo) != JavaReflectionInvocador.invocarMetodoGet(atributoObjeto, objetoAntigo)) {

						isIgual = false;

					}

				} else {

					if (!JavaReflectionInvocador.invocarMetodoGet(atributoObjeto, objetoNovo).equals(JavaReflectionInvocador.invocarMetodoGet(atributoObjeto, objetoAntigo))) {

						isIgual = false;

					}

				}

			} else {

				if (JavaReflectionInvocador.invocarMetodoGet(atributoObjeto, objetoAntigo) != null)
					isIgual = false;

			}

		} else if (atributoObjeto.getAnnotation(ManyToOne.class) != null || atributoObjeto.getAnnotation(OneToOne.class) != null) {

			isIgual = isPropriedadeComposicaoIgual(JavaReflectionInvocador.invocarMetodoGet(atributoObjeto, objetoNovo), JavaReflectionInvocador.invocarMetodoGet(atributoObjeto, objetoAntigo));

		}

		return isIgual;

	}

	/**
	 * Configura valorAtributo em instancia no campo atributoObjeto da classe clazz.
	 * 
	 * @param valorAtributo a ser configurado.
	 * @param instancia para configuração da propriedade.
	 * @param atributoObjeto o qual deverá ser configurado em instancia.
	 * @param clazz é a Classe a qual pertence instancia.
	 * @return instancia já previamente configurada com valorAtributo no atributoObjeto correto. Se instancia for nula, retorna nova instância com o valor configurado.
	 * @throws InfraException 
	 */
	public static <T, K> T configurarPropriedade(K valorAtributo, T instancia, Field atributoObjeto, Class<T> clazz) throws InfraException {

		if (instancia == null) {

			try {

				instancia = clazz.newInstance();

			} catch (InstantiationException e) {

				throw new InfraException(FormatarDados.montarMensagem("Erro ao tentar instanciar uma nova instância de {0}.", clazz.getCanonicalName()));

			} catch (IllegalAccessException e) {

				throw new InfraException(FormatarDados.montarMensagem("O construtor padrão não existe ou não há acesso para o mesmo. Erro ao instanciar {0}.", clazz.getCanonicalName()));

			}

		}

		JavaReflectionInvocador.invocarMetodoSet(atributoObjeto, instancia, valorAtributo);

		return instancia;

	}

	/**
	 * Método responsável por validar se houve alterações nas listas conhecidas do modelo. Uma lista conhecida é toda aquela que é administrada diretamente dentro do contexto
	 * do modelo desejado. Por exemplo: uma Pessoa contém Dependentes, esses Dependentes são responsabilidade direta de uma Pessoa, logo a lista de Dependentes é uma lista conhecida.
	 * Portanto não somente verifica a troca dos itens de uma lista, mas as alterações provocadas nessa.
	 * 
	 * @param listaNova lista alterada com valores novos.
	 * @param listaAntiga a lista antiga com os estados antigos.
	 * @param propriedadeIdentificadora é aquela que distingue uma instância de outra.
	 * @param clazz é a Classe a qual os itens das listas listaNova e listaBancoDados representam.
	 * @return true, se não foi alterado; false, se foi alterado.
	 * @throws InfraException tradução das exceptions lançadas durante o corpo do método.
	 * @throws NullPointerException caso um dos argumentos seja nulo.
	 */
	public static <I> Boolean verificarAlteracaoItensListaConhecida(List<I> listaNova, List<I> listaAntiga, String propriedadeIdentificadora, Class<I> clazz) throws InfraException,
			NullPointerException {

		Boolean isTodosIguais = true;

		if (listaNova == null || listaAntiga == null || UtilJava.isStringVazia(propriedadeIdentificadora, true) || clazz == null)
			throw new NullPointerException("As informações listaNova, listaBancoDados, propriedadeCodigo e clazz não podem ser nulo.");

		else {

			if (isDiferencas(listaNova, listaAntiga, propriedadeIdentificadora, clazz)) {

				isTodosIguais = false;

			}

			if (isAdicionados(listaNova, listaAntiga, propriedadeIdentificadora, clazz)) {

				isTodosIguais = false;

			}

			if (isRemovidos(listaNova, listaAntiga, propriedadeIdentificadora, clazz)) {

				isTodosIguais = false;

			}

		}

		return isTodosIguais;

	}

	public static <I> Boolean isDiferencas(List<I> listaNova, List<I> listaAntiga, String propriedadeIdentificadora, Class<I> clazz) throws InfraException {

		Boolean isTodosIguais = true;

		if (listaNova == null || listaAntiga == null || UtilJava.isStringVazia(propriedadeIdentificadora, true) || clazz == null)
			throw new NullPointerException("As informações listaNova, listaBancoDados, propriedadeCodigo e clazz não podem ser nulo.");

		else {

			for (I itemBancoDadosValidacao : listaAntiga) {

				for (I itemNovoValidacao : listaNova) {

					if (invocarMetodoGet(propriedadeIdentificadora, itemNovoValidacao) != null
							&& invocarMetodoGet(propriedadeIdentificadora, itemBancoDadosValidacao).equals(invocarMetodoGet(propriedadeIdentificadora, itemNovoValidacao))) {

						isTodosIguais = verificarAlteracaoDadosSimplesEEntidadesComposicao(itemNovoValidacao, itemBancoDadosValidacao, clazz);

						break;

					}

				}

				if (!isTodosIguais)
					break;

			}

		}

		return !isTodosIguais;

	}

	public static <I> Boolean isAdicionados(List<I> listaNova, List<I> listaAntiga, String propriedadeIdentificadora, Class<I> clazz) throws InfraException {

		Boolean naoEncontrado = true;

		if (listaNova == null || listaAntiga == null || UtilJava.isStringVazia(propriedadeIdentificadora, true) || clazz == null)
			throw new NullPointerException("As informações listaNova, listaBancoDados, propriedadeCodigo e clazz não podem ser nulo.");

		else {

			for (I itemNovoValidacao : listaNova) {

				naoEncontrado = true;

				for (I itemBancoDadosValidacao : listaAntiga) {

					if (invocarMetodoGet(propriedadeIdentificadora, itemNovoValidacao) != null
							&& invocarMetodoGet(propriedadeIdentificadora, itemBancoDadosValidacao).equals(invocarMetodoGet(propriedadeIdentificadora, itemNovoValidacao))) {

						naoEncontrado = false;

						break;

					}

				}

				if (naoEncontrado)
					break;

			}

		}

		return naoEncontrado;

	}

	public static <I> Boolean isRemovidos(List<I> listaNova, List<I> listaAntiga, String propriedadeIdentificadora, Class<I> clazz) throws InfraException {

		Boolean naoEncontrado = true;

		if (listaNova == null || listaAntiga == null || UtilJava.isStringVazia(propriedadeIdentificadora, true) || clazz == null)
			throw new NullPointerException("As informações listaNova, listaBancoDados, propriedadeCodigo e clazz não podem ser nulo.");

		else {

			for (I itemBancoDadosValidacao : listaAntiga) {

				naoEncontrado = true;

				for (I itemNovoValidacao : listaNova) {

					if (invocarMetodoGet(propriedadeIdentificadora, itemBancoDadosValidacao) != null
							&& invocarMetodoGet(propriedadeIdentificadora, itemBancoDadosValidacao).equals(invocarMetodoGet(propriedadeIdentificadora, itemNovoValidacao))) {

						naoEncontrado = false;

						break;

					}

				}

				if (naoEncontrado)
					break;

			}

		}

		return naoEncontrado;

	}

	/**
	 * Verifica se os objetos de agregação ou composição foram alterados.
	 * 
	 * @author andre.mello.
	 * @param objetoComposicaoNovo Objeto que contenha a anotação embedabble e que corresponda ao novo estado.
	 * @param objetoComposicaoAntigo Objeto que contenha a anotação embedabble e que corresponda ao estado em banco de dados e que será alterado.
	 * @return true, se não foi alterado; false, se foi alterado.
	 * @throws InfraException tradução das exceções lançadas durante a execução do método.
	 */
	public static <C, T> Boolean isPropriedadeComposicaoIgual(C objetoComposicaoNovo, C objetoComposicaoAntigo) throws InfraException {

		Field[] atributosEntidadeDeComposicao = null;
		Boolean isIgual = true;

		if (objetoComposicaoNovo != null) {

			atributosEntidadeDeComposicao = objetoComposicaoNovo.getClass().getDeclaredFields();

			for (Field atributosEntidadeDeComposicaoValidacao : atributosEntidadeDeComposicao) {

				if (atributosEntidadeDeComposicaoValidacao.getAnnotation(EmbeddedId.class) != null) {

					if (JavaReflectionInvocador.invocarMetodoGet(atributosEntidadeDeComposicaoValidacao, objetoComposicaoNovo) != null) {

						if (!isEmbeddedIdIgual(JavaReflectionInvocador.invocarMetodoGet(atributosEntidadeDeComposicaoValidacao, objetoComposicaoNovo), JavaReflectionInvocador.invocarMetodoGet(
								atributosEntidadeDeComposicaoValidacao,
								objetoComposicaoAntigo)))
							isIgual = false;

					} else if (JavaReflectionInvocador.invocarMetodoGet(atributosEntidadeDeComposicaoValidacao, objetoComposicaoAntigo) != null)
						isIgual = false;

					break;

				} else if (atributosEntidadeDeComposicaoValidacao.getAnnotation(Id.class) != null) {

					if (JavaReflectionInvocador.invocarMetodoGet(atributosEntidadeDeComposicaoValidacao, objetoComposicaoNovo) != null) {

						if (!(JavaReflectionInvocador.invocarMetodoGet(atributosEntidadeDeComposicaoValidacao, objetoComposicaoNovo)).equals(JavaReflectionInvocador.invocarMetodoGet(
								atributosEntidadeDeComposicaoValidacao,
								objetoComposicaoAntigo))) {

							isIgual = false;

						}

					} else if (JavaReflectionInvocador.invocarMetodoGet(atributosEntidadeDeComposicaoValidacao, objetoComposicaoAntigo) != null)
						isIgual = false;

					break;

				}

			}

		} else if (objetoComposicaoAntigo != null) {

			isIgual = false;

		}

		return isIgual;

	}

	/**
	 * Verifica se os objetos contêm o mesmo estado.
	 * 
	 * @author andre.mello
	 * @param embeddedIdObjetoNovo o objeto que contém a anotação Embeddable para a instância nova.
	 * @param embeddedIdObjetoBancoDados  o objeto que contém a anotação Embeddable para a instância em banco de dados e que será alterada.
	 * @return true, se tiverem estado igual; false, caso contrário.
	 * @throws InfraException tradução das exceções lançadas.
	 */
	private static <T> Boolean isEmbeddedIdIgual(T embeddedIdObjetoNovo, T embeddedIdObjetoBancoDados) throws InfraException {

		Field[] atributosEmbeddedId = null;
		Boolean todasChavesIguais = true;

		if (embeddedIdObjetoNovo == null || embeddedIdObjetoBancoDados == null) {

			throw new InfraException("Os valores embeddedIdObjetoNovo e embeddedIdObjetoBancoDados não podem ser nulo.");

		}

		atributosEmbeddedId = embeddedIdObjetoNovo.getClass().getDeclaredFields();

		for (Field atributosEmbeddedIdValidacao : atributosEmbeddedId) {

			if (atributosEmbeddedIdValidacao.getAnnotation(Column.class) != null) {

				if (invocarMetodoGet(atributosEmbeddedIdValidacao, embeddedIdObjetoNovo) != null) {

					if (!invocarMetodoGet(atributosEmbeddedIdValidacao, embeddedIdObjetoNovo).equals(invocarMetodoGet(atributosEmbeddedIdValidacao, embeddedIdObjetoBancoDados)))
						todasChavesIguais = false;

				} else if (invocarMetodoGet(atributosEmbeddedIdValidacao, embeddedIdObjetoBancoDados) != null)
					todasChavesIguais = false;

			}

		}

		return todasChavesIguais;

	}

	/**
	 * Métodos responsável por verificar as alterações correspondentes aos relacionamentos e correspondentes às propriedades wrappers de uma entidade.
	 * Lembrando que as alterações percebidas nos relacionamentos não implicam aquelas propriedades da entidade estrangeira, somente a alteração correspondente
	 * ao relacionamento, propriamente dito.
	 * 
	 * @author andre.mello
	 * @param objetoNovo novo utilizado para comparação com o objetoBancoDados.
	 * @param objetoBancoDados existente em banco de dados que terá seus dados verificados.
	 * @return true, se forem propriedades iguais; false, se diferentes.
	 * @throws InfraException tradução das exceptions lançadas no corpo do método.
	 */
	public static <T> Boolean verificarAlteracaoDadosSimplesEEntidadesComposicao(T objetoNovo, T objetoBancoDados, Field atributoClasseT) throws InfraException {

		Boolean iguais = true;

		iguais = compararPropriedades(atributoClasseT, objetoNovo, objetoBancoDados);

		return iguais;

	}

	/**
	 * Verifica se objetoNovo é igual objetoBancoDados e configura um novo objeto do tipo T com os valores diferentes.
	 * 
	 * @author andre.mello
	 * @param atributoObjeto que possívelmente foi alterado.
	 * @param objetoNovo uma nova instância de T somente com as informações alteradas.
	 * @param objetoAntigo uma instância previamente existente de T.
	 * @return true, se não foi alterado; false, se não foi alterado.
	 * @throws InfraException 
	 */
	public static <T> Boolean compararPropriedades(Field atributoObjeto, T objetoNovo, T objetoAntigo) throws InfraException {

		Boolean isIgual = true;

		if (atributoObjeto.getAnnotation(Column.class) != null) {

			if (!"".equals(JavaReflectionInvocador.invocarMetodoGet(atributoObjeto, objetoNovo)) && JavaReflectionInvocador.invocarMetodoGet(atributoObjeto, objetoNovo) != null) {

				if (atributoObjeto.getType().isPrimitive()) {

					if (JavaReflectionInvocador.invocarMetodoGet(atributoObjeto, objetoNovo) != JavaReflectionInvocador.invocarMetodoGet(atributoObjeto, objetoAntigo)) {

						isIgual = false;

					}

				} else {

					if (!JavaReflectionInvocador.invocarMetodoGet(atributoObjeto, objetoNovo).equals(JavaReflectionInvocador.invocarMetodoGet(atributoObjeto, objetoAntigo))) {

						isIgual = false;

					}

				}

			} else {

				if (!"".equals(JavaReflectionInvocador.invocarMetodoGet(atributoObjeto, objetoAntigo)) && JavaReflectionInvocador.invocarMetodoGet(atributoObjeto, objetoAntigo) != null)
					isIgual = false;

			}

		} else if (atributoObjeto.getAnnotation(ManyToOne.class) != null || atributoObjeto.getAnnotation(OneToOne.class) != null) {

			isIgual = isPropriedadeComposicaoIgual(JavaReflectionInvocador.invocarMetodoGet(atributoObjeto, objetoNovo), JavaReflectionInvocador.invocarMetodoGet(atributoObjeto, objetoAntigo));

		}

		return isIgual;

	}

	/**
	 * Acessa o nomeCampo da clazz desejada.
	 * 
	 * @param nomeCampo do campo a ser acessado.
	 * @param clazz a ter nomeCampo acessado.
	 * @return o Field correspondente ao campo acessado.
	 * @throws InfraException tradução dos erros ocorridos durante a execução do método.
	 */
	public static <T> Field acessaCampo(String nomeCampo, Class<T> clazz) throws InfraException {

		if (nomeCampo == null && clazz == null)
			throw new InfraException("Os valores nomeCampo e clazz não podem ser nulo.");

		else {

			try {
				return clazz.getDeclaredField(nomeCampo);
			} catch (SecurityException e) {

				throw new InfraException(FormatarDados.montarMensagem("Não há acesso ao atributo {0} da classe {0}.", nomeCampo, clazz.getCanonicalName()), e.getCause());

			} catch (NoSuchFieldException e) {

				throw new InfraException(FormatarDados.montarMensagem("Não há existe o atributo {0} da classe {0}.", nomeCampo, clazz.getCanonicalName()), e.getCause());

			}

		}

	}

}
