package br.com.loogix.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = LocalDate.class)
public class LocalDateConverter implements Converter, Serializable {

    private static final long serialVersionUID = 1L;

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            try {
                return LocalDate.parse(value, formatter);
            } catch (DateTimeParseException ex) {
                String mensagem = String.format("A data %s não está no formato correto.", value);
                throw new ConverterException(mensagem);
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof LocalDate) {
            LocalDate localDate = (LocalDate) value;
            if (localDate != null) {
                return localDate.format(formatter);
            }
        }
        return "";
    }

}