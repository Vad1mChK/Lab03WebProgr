package org.vad1mchk.webprogr.lab03.beans;

import javax.annotation.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.time.ZoneOffset;

@ViewScoped
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

    public void updateZone() {
        this.zone = ZoneOffset.ofTotalSeconds(zone.getTotalSeconds());
    }

    public ZoneOffset getZone() {
        return zone;
    }
}
