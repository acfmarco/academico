package br.com.iasc.seguranca.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.iasc.seguranca.Entidades.BaseEntity;


/**
 * Esta classe fornece um conversor genérico para entidades que implementam a
 * interface BaseEntity. É necessário que objeto possua o codigo populado para
 * que o objeto possa ser adicionado ao mapa do componente.
 * 
 * 
 * @author tiago.menezes
 */
@FacesConverter(value = "entityConverter")
public class EntityConverter implements Converter {

	/**
	 * @return Object Retorna o objeto BaseEntity
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null) {
			return this.getAttributesFrom(component).get(value);
		}
		return null;
	}

	/**
	 * @return String Retorna o código do objeto do tipo BaseEntity 
	 * convertido em String.
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		boolean resultado = false;

		if (value != null && !"".equals(value)) {
			BaseEntity entity = (BaseEntity) value;

			// armazena o objeto do tipo BaseEntity no mapa de atributos.
			resultado = this.addAttribute(component, entity);

			Long codigo = entity.getCodigo();
			if (codigo != null) {
				return String.valueOf(codigo);
			}
		}

		if (resultado) {

			return (String) value;
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
	protected boolean addAttribute(UIComponent component, BaseEntity o) {

		String key = new String();
		boolean adicionou = false;

		if (!(o.getCodigo() == null)) {
			key = o.getCodigo().toString();
			this.getAttributesFrom(component).put(key, o);
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
