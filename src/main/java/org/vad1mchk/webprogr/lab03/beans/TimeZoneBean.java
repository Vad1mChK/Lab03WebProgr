package org.vad1mchk.webprogr.lab03.beans;

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
        zones = Collections.unmodifiableMap(new LinkedHashMap<>() {{
            put("Washington, D.C. (UTC-5)", ZoneOffset.ofHours(-5).getTotalSeconds());
            put("Москва (UTC+3)", ZoneOffset.ofHours(3).getTotalSeconds());
            put("北京 (UTC+8)", ZoneOffset.ofHours(8).getTotalSeconds());
            put("東京 (UTC+9)", ZoneOffset.ofHours(9).getTotalSeconds());
        }});
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
