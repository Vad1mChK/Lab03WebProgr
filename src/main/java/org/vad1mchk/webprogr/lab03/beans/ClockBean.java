package org.vad1mchk.webprogr.lab03.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@RequestScoped
@Named
public class ClockBean implements Serializable {
    private DateTimeFormatter formatter;

    @Inject
    private TimeZoneBean zoneBean;

    public ClockBean() {
        super();
        formatter = DateTimeFormatter.ofPattern("MMM d, YYYY — HH:mm:ss", new Locale("ru", "RU"));
    }

    public String getTimeString() {
        ZoneOffset zone = zoneBean.getZone();
        System.out.println("zone: " + zone);
        return zone != null ? formatter.format(LocalDateTime.now(zone)) : "[ДАННЫЕ УДАЛЕНЫ]";
    }
}