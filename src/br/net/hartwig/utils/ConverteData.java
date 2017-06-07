package br.net.hartwig.utils;

import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;

/**  
 * S.G.V 2017
 * Author: Diego Michel Hartwig
 */
@FacesConverter("dateConverter")
public class ConverteData implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value){
		if(!value.isEmpty() && value != null){
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss");
			Calendar calendar = Calendar.getInstance();
			try{
				calendar.setTime(df.parse(value));
			} catch (ParseException e){
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
							"N�o foi possivel converter a data. Formato Inv�lido!");
					context.addMessage(null, msg);	
			}
				return calendar;
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
	if(!value.toString().isEmpty() && value !=null){
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss");
		Calendar calendar = (Calendar) value;
		return df.format(calendar.getTime());
		
		}		
		
		return null;
	}	
	

}
