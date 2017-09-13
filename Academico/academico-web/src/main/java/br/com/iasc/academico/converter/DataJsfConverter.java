package br.com.iasc.academico.converter;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.iasc.seguranca.util.Mensagens;
import br.com.iasc.utils.UtilJava;
import br.com.iasc.utils.data.UtilData;

/**
 * Classe de conversão de objetos em java para jsf.
 * Converte a data formatada no jsf e desconverte a mesma para o objeto java.
 * 
 * @author tiago.menezes
 */
@FacesConverter(value = "dataJsfConverter")
public class DataJsfConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String dataFormatada) {
		try {

			return UtilData.getDataValida(dataFormatada);

		} catch (ParseException e) {
			throw new ConverterException(Mensagens.getFacesMessageErro("ME106", dataFormatada));
		}

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object obj) {

		if (obj != null) {

			if (obj instanceof Calendar) {
				Calendar data = (Calendar) obj;
				return UtilJava.formataCalendarPorPadrao(data, UtilJava.DIA_MES_ANO);
			}
			if (obj instanceof Date) {
				Date data = (Date) obj;
				return UtilJava.formataDataPorPadrao(data, UtilJava.DIA_MES_ANO);
			}
		}
		return null;
	}

}
