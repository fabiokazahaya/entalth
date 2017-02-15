package br.com.enthalt.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("cpfConverter")
public class CpfConverter implements Converter {
    
    public static final String VAZIO = "";
    public static final String PONTO = ".";
    public static final String TRACO = "-";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uIcomponent, String cpf) {
        if (cpf.trim().equals(VAZIO)) {
            return null;
        } else {
           cpf = cpf.replace(PONTO, VAZIO);
           cpf = cpf.replace(TRACO, VAZIO);
           return cpf;
       }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uIcomponent, Object object) {
        return object.toString();
    }

}
