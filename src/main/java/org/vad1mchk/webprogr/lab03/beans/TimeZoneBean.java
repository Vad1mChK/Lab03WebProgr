package org.vad1mchk.webprogr.lab03.beans;

import org.vad1mchk.webprogr.lab03.util.ZoneOption;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.time.ZoneOffset;
import java.util.*;

@SessionScoped
@Named("zoneBean")
public class TimeZoneBean implements Serializable {
    private static final Map<String, Integer> zones;
    private int offsetSeconds;
    private ZoneOffset zone;

    static {
        zones = Collections.unmodifiableMap(new LinkedHashMap<>());
        for (ZoneOption option : ZoneOption.values()) {
            zones.put(option.getZoneName(), option.getZoneOffset().getTotalSeconds());
        }
    }

    public Map<String, Integer> getZones() {
        return zones;
    }

    public int getOffsetSeconds() {
        return offsetSeconds;
    }

    public void setOffsetSeconds(int offsetSeconds) {
        this.offsetSeconds = offsetSeconds;
        updateZone();
    }

    private void updateZone() {
        this.zone = ZoneOffset.ofTotalSeconds(offsetSeconds);
    }

    public ZoneOffset getZone() {
        return zone;
    }
}
