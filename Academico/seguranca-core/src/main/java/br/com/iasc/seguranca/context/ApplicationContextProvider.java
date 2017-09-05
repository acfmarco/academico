package br.com.iasc.seguranca.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Esta classe provê o acesso ao contexto do spring de qualquer lugar da
 * aplicação esteja o arquivo de configuração do spring.
 * 
 * Use AppContext.getApplicationContext() para acessar os beans gerenciados pelo
 * Spring.
 * 
 * @author tiago.menezes
 */
public class ApplicationContextProvider implements ApplicationContextAware {

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		AppContext.setApplicationContext(applicationContext);
	}

}
