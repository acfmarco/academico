/**
 * 
 */
package br.com.iasc.academico.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.iasc.academico.Entidades.BaseModel;

/**
 * Conversor genérico de entidades.
 * 
 * @author tiago.menezes
 * @since 13/08/2012
 */
@FacesConverter(value = "baseConverter")
public class BaseConverter implements Converter {

	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String valor) {

		if (valor != null) {
			return this.getAttributesFrom(component).get(valor);
		}

		return null;
	}

	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {

		boolean resultado = false;

		if (object != null && !"".equals(object)) {
			BaseModel model = (BaseModel) object;

			// armazena o objeto do tipo BaseEntity no mapa de atributos.
			resultado = this.addAttribute(component, model);

			Object codigo = model.getCodigo();
			if (codigo != null) {
				return String.valueOf(codigo);
			}
		}

		if (resultado) {
			return (String) object;
		} else {
			return null;
		}
	}

	/**
	 * Adiciona o objeto BaseEntity no mapa, sendo a chave o código da entidade.
	 * 
	 * @param component
	 * @param o objeto do tipo BaseEntity
	 */
	protected boolean addAttribute(UIComponent component, BaseModel model) {

		String key = new String();
		boolean adicionou = false;

		if (!(model.getCodigo() == null)) {
			key = model.getCodigo().toString();
			this.getAttributesFrom(component).put(key, model);
			adicionou = true;
		}

		return adicionou;
	}

	/**
	 * Recupera o mapa de objetos baseEntity
	 * 
	 * @param component
	 * @return Map<String, Object>
	 */
	protected Map<String, Object> getAttributesFrom(UIComponent component) {
		return component.getAttributes();
	}

}
