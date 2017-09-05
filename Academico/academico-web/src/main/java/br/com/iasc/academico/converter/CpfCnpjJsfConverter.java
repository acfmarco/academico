package br.com.iasc.academico.converter;

import java.text.ParseException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.swing.text.MaskFormatter;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

@Component(value = "cpfcnpjjsfConverter")
public class CpfCnpjJsfConverter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
		String cpfCnpj = value;
		if (StringUtils.isNotBlank(value)) {
			cpfCnpj = value.replaceAll("\\D", "");
		}
		return cpfCnpj;
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
		String cpfCnpj = null;
		System.out.println("Teste");
		if (value instanceof String) {
			cpfCnpj = (String) value;
		} else {
			cpfCnpj = String.valueOf(value);
		}

		if (cpfCnpj.length() > 0 && cpfCnpj.length() < 11) {
			cpfCnpj = StringUtils.leftPad(cpfCnpj, 11, '0');
		} else {
			if (cpfCnpj.length() > 11 && cpfCnpj.length() < 14) {
				cpfCnpj = StringUtils.leftPad(cpfCnpj, 14, '0');
			}
		}

		if (StringUtils.isNotBlank(cpfCnpj)) {
			try {
				if (cpfCnpj.length() == 11) {
					MaskFormatter mf = new MaskFormatter("###.###.###-##");
					mf.setValueContainsLiteralCharacters(false);
					cpfCnpj = mf.valueToString(cpfCnpj);
				} else if (cpfCnpj.length() == 14) {
					MaskFormatter mf = new MaskFormatter("##.###.###/####-##");
					mf.setValueContainsLiteralCharacters(false);
					cpfCnpj = mf.valueToString(cpfCnpj);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return cpfCnpj;
	}
}