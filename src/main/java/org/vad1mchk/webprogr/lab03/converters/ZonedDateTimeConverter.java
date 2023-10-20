package org.vad1mchk.webprogr.lab03.converters;

import org.vad1mchk.webprogr.lab03.beans.TimeZoneBean;

import javax.faces.annotation.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;

@FacesConverter(forClass = ZonedDateTime.class, value = "zonedDateTimeConverter", managed = true)
public class ZonedDateTimeConverter extends DateTimeConverter {

    public static final Locale RUSSIAN_LOCALE = new Locale("ru", "RU");

    @ManagedProperty(value="#{timeZoneBean}")
    private TimeZoneBean zoneBean;

    public ZonedDateTimeConverter() {
        setLocale(RUSSIAN_LOCALE);
        setPattern("dd.MM.yyyy HH:mm:ss (O)");
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(getPattern(), getLocale());
        return ZonedDateTime.parse(value, formatter);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof ZonedDateTime) {
            DateTimeFormatter formatter = DateTimeFormatter
                    .ofPattern(getPattern(), getLocale())
                    .withZone(zoneBean.getZone());
            return formatter.format((ZonedDateTime) value);
        }
        return null;
    }

    public TimeZoneBean getZoneBean() {
        return zoneBean;
    }

    public void setZoneBean(TimeZoneBean zoneBean) {
        this.zoneBean = zoneBean;
    }
}
