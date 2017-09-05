package br.com.iasc.academico.converter;

import java.text.ParseException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.swing.text.MaskFormatter;

import org.apache.commons.lang.StringUtils;

public class CepJsfConverter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
		String cep = value;
		if (StringUtils.isNotBlank(value)) {
			cep = value.replaceAll("\\D", "");
		}
		return cep;
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
		String cep = null;
		if (value instanceof String) {
			cep = (String) value;
			if (StringUtils.isNotBlank(cep) && cep.length() == 8) {
				try {
					MaskFormatter mf = new MaskFormatter("##.###-###");
					mf.setValueContainsLiteralCharacters(false);
					cep = mf.valueToString(cep);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		return cep;
	}
}