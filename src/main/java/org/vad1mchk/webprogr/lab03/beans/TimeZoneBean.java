package org.vad1mchk.webprogr.lab03.beans;

import org.vad1mchk.webprogr.lab03.util.ZoneOption;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneOffset;

@SessionScoped
@Named("zoneBean")
public class TimeZoneBean implements Serializable {
    private ZoneOffset zone;

    public int getOffsetSeconds() {
        return zone != null ?
                zone.getTotalSeconds() :
                ZoneOffset.systemDefault().getRules().getOffset(Instant.now()).getTotalSeconds();
    }

    public void setOffsetSeconds(int offsetSeconds) {
        System.out.println("Zone offset in seconds is now " + offsetSeconds);
        setZone(ZoneOffset.ofTotalSeconds(offsetSeconds));
    }

    public ZoneOffset getZone() {
        return zone;
    }

    public void setZone(ZoneOffset zone) {
        System.out.println("Zone is now " + zone);
        this.zone = zone;
    }

    public ZoneOption[] getAvailableZones() {
        return ZoneOption.values();
    }
}
