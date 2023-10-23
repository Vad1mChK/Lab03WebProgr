package org.vad1mchk.webprogr.lab03.converters;

import org.vad1mchk.webprogr.lab03.beans.TimeZoneBean;

import javax.faces.annotation.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.logging.Logger;

@FacesConverter(forClass = ZonedDateTime.class, value = "zonedDateTimeConverter", managed = true)
public class ZonedDateTimeStringConverter extends DateTimeConverter {

    public static final Locale RUSSIAN_LOCALE = new Locale("ru", "RU");

    @ManagedProperty(value="#{timeZoneBean}")
    private TimeZoneBean zoneBean;

    public ZonedDateTimeStringConverter() {
        setLocale(RUSSIAN_LOCALE);
        setPattern("dd.MM.yyyy HH:mm:ss (O)");
    }

    private ZoneId zone() {
        if (zoneBean == null || zoneBean.getZone() == null) return ZoneOffset.systemDefault();
        return zoneBean.getZone();
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println("Converting string \"" + value + "to object");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(getPattern(), getLocale());
        return ZonedDateTime.parse(value, formatter);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        System.out.println("Converting object \"" + value + "to string");
        if (value instanceof ZonedDateTime) {
            DateTimeFormatter formatter = DateTimeFormatter
                    .ofPattern(getPattern(), getLocale())
                    .withZone(zone());
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
