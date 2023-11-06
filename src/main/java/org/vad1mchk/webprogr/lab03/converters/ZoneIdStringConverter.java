package org.vad1mchk.webprogr.lab03.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;

@FacesConverter(value = "zoneIdConverter")
public class ZoneIdStringConverter implements Converter<ZoneOffset> {
    private final DateTimeFormatter offsetFormatter;

    public ZoneIdStringConverter() {
        this.offsetFormatter = DateTimeFormatter.ofPattern("xxx");
    }

    @Override
    public ZoneOffset getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        try {
            TemporalAccessor temporal = offsetFormatter.parse(value);
            return ZoneOffset.from(temporal);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, ZoneOffset value) {
        if (value == null) {
            return null;
        }
        return offsetFormatter.format(value);
    }
}
