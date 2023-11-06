package org.vad1mchk.webprogr.lab03.beans;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

@SessionScoped
@Named("zoneBean")
public class TimeZoneBean implements Serializable {
    private int offsetSeconds;
    private ZoneOffset zone;

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
