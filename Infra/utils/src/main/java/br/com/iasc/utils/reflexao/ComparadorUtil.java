package br.com.iasc.utils.reflexao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ComparadorUtil {

	@SuppressWarnings("unused")
	private static String getNomeMetodoGet(String nomeAtributo, Class<?> c) throws SecurityException, NoSuchFieldException {
		Field f = c.getDeclaredField(nomeAtributo);
		String possivelNome, nomeMetodoRetorno;
		nomeMetodoRetorno = "";
		nomeMetodoRetorno = "";
		if (f.getType().getName().toUpperCase().equalsIgnoreCase("boolean"))
			possivelNome = "IS" + nomeAtributo.toUpperCase();
		else
			possivelNome = "GET" + nomeAtributo.toUpperCase();
		Method m[] = c.getDeclaredMethods();
		for (int i = 0; i < m.length; i++) {
			if (m[i].getName().toUpperCase().equalsIgnoreCase(possivelNome)) {
				nomeMetodoRetorno = m[i].getName();
			}
		}

		return nomeMetodoRetorno;
	}
}
