package br.com.iasc.academico.converter;

import java.text.ParseException;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.iasc.utils.UtilJava;
import br.com.iasc.utils.data.UtilData;

/**
 * @author tiago.menezes
 *
 */
@FacesConverter(value = "dataHoraJsfConverter")
public class DataHoraJsfConverter implements Converter {

	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		try {

			if (!UtilJava.isStringVazia(value)) {
				Date dataConversao = UtilData.parseToDate(value);
				return dataConversao;
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object objeto) {

		if (objeto != null) {
			String dataFormatada = UtilJava.formataDataPorPadrao((Date) objeto, UtilJava.DIA_MES_ANO_HORA_MIN_SEGUNDO);
			return dataFormatada;
		}

		return null;
	}

}
