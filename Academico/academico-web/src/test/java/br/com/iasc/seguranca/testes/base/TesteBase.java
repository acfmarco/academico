package br.com.iasc.seguranca.testes.base;

import junit.framework.TestCase;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Classe básica para testes unitários no projeto. 
 *
 * @author Lourival Júnior
 * @since 01/10/2012
 * 
 * Copyright notice (c) 2012 BBPrevidência S/A
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:contexto-spring-teste.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
public abstract class TesteBase extends TestCase {

}