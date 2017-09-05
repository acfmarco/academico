package br.com.iasc.academico.converter;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang.StringUtils;

import br.com.iasc.seguranca.util.Mensagens;
import br.com.iasc.utils.UtilJava;
import br.com.iasc.utils.data.UtilData;

/**
 * Convertr JSF para data de competência. Este tipo de data aceita apenas o mês e o ano (MM/YYYY).
 * 
 * @author Lourival Júnior
 * @since 05/09/2013
 * 
 * Copyright notice (c) 2013 BBPrevidência S/A
 */
@FacesConverter(value = "dataCompetenciaConverter")
public class DataCompetenciaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String dataFormatada) {
		if (StringUtils.isEmpty(dataFormatada)) {
			return null;
		}
		try {
			return UtilData.getDataValida("01/" + dataFormatada);
		} catch (ParseException e) {
			throw new ConverterException(Mensagens.getFacesMessageErro("ME106", dataFormatada));
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object obj) {
		if (obj != null) {
			if (obj instanceof Calendar) {
				Calendar data = (Calendar) obj;
				return UtilJava.formataCalendarPorPadrao(data, "MM/yyyy");
			}
			if (obj instanceof Date) {
				Date data = (Date) obj;
				return UtilJava.formataDataPorPadrao(data, "MM/yyyy");
			}
		}
		return null;
	}
}