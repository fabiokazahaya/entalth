package br.com.enthalt.converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.enthalt.managedbeans.AlunoMB;
import br.com.enthalt.model.Curso;

@FacesConverter("cursoConverter")
public class CursoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
	
	if (value != null && value.trim().length() > 0) {
	    try {
		AlunoMB service = (AlunoMB) fc.getExternalContext().getApplicationMap().get("alunoMB");
		return service.getCursos().get(Integer.parseInt(value));
	    } catch (NumberFormatException e) {
		throw new ConverterException(
			new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
	    }
	} else {
	    return null;
	}
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
	if (object != null) {
	    return String.valueOf(((Curso) object).getId());
	} else {
	    return null;
	}
    }
}



