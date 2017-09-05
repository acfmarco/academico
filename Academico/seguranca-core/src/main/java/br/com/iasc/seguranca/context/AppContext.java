package br.com.iasc.seguranca.context;

import org.springframework.context.ApplicationContext;

/**
 * Esta classe provê a aplicação acesso ao contexto do spring.
 * A propriedade applicationContext é injetada através da classe {@link ApplicationContextProvider#} 
 * 
 * @author tiago.menezes
 *
 */
public class AppContext {

	private static ApplicationContext applicationContext;

	/**
	 * @return ApplicationContext
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * @param applicationContext 
	 */
	public static void setApplicationContext(ApplicationContext applicationContext) {
		AppContext.applicationContext = applicationContext;
	}

}
