package br.com.iasc.academico.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.iasc.utils.UtilJava;

/**
 * Esta classe fornece um conversor genérico para entidades que implementam a
 * interface BaseEntity. É necessário que objeto possua o codigo populado para
 * que o objeto possa ser adicionado ao mapa do componente.
 * 
 * 
 * @author tiago.menezes
 */
@FacesConverter(value = "floatConverter")
public class FloatConverter implements Converter {

	/**
	 * @return Object Retorna o objeto BaseEntity
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && !"".equals(value)) {
			String valor = String.valueOf(UtilJava.desformataNumero(value));
			return Float.valueOf(valor);
		}
		return null;
	}

	/**
	 * @return String Retorna o código do objeto do tipo BaseEntity 
	 * convertido em String.
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value != null && !"".equals(value)) {
			Float valor = (Float) value;

			if (valor != null) {
				return String.valueOf(valor);
			}
		}

		return null;
	}
}
