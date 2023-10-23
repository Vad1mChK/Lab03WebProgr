package org.vad1mchk.webprogr.lab03.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@FacesConverter(forClass = List.class, value = "bigDecimalListConverter", managed = true)
public class BigDecimalListStringConverter implements Converter<List<BigDecimal>> {
    @Override
    public List<BigDecimal> getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) return null;
        try {
            return Arrays
                    .stream(value.replace("[", "").replace("]", "").split(","))
                    .map(BigDecimal::new)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, List<BigDecimal> value) {
        return value != null ? value.toString() : "";
    }
}