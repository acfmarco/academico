/**
 * 
 */
package br.com.iasc.academico.converter;

import java.math.BigDecimal;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.iasc.utils.UtilJava;

/**
 *
 * @author tiago.menezes
 * @since 05/10/2012
 * 
 * Copyright notice (c) 2012 BBPrevidência S/A 
 */
@FacesConverter(value = "bigDecimalConverter")
public class BigDecimalConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && !"".equals(value)) {
			String valor = String.valueOf(UtilJava.desformataNumero(value));
			return new BigDecimal(valor);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && !"".equals(value)) {
			BigDecimal valor = (BigDecimal) value;
			if (valor != null) {
				return String.valueOf(valor);
			}
		}
		return null;
	}

}
