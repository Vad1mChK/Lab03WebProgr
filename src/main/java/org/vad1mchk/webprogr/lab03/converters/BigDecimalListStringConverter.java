package org.vad1mchk.webprogr.lab03.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@FacesConverter(forClass = List.class, value = "bigDecimalListConverter", managed = true)
public class BigDecimalListStringConverter implements Converter<List<BigDecimal>> {
    @Override
    public List<BigDecimal> getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            System.out.println("null was passed to bigDecimalListConverter");
            return null;
        }
        System.out.println("Converting string '" + value + "' to list of BigDecimal");
        try {
            return Arrays
                    .stream(value
                            .replace("[", "")
                            .replace("]", "")
                            .replace(" ", "")
                            .split(","))
                    .map(BigDecimal::new)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, List<BigDecimal> value) {
        return value != null ? value.toString() : "";
    }
}