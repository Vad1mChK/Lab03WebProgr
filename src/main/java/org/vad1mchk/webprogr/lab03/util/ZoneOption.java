package org.vad1mchk.webprogr.lab03.util;

import java.time.ZoneOffset;

public enum ZoneOption {
    // no need to account for time saving, this is just a lab after all
    WASHINGTON_DC( "UTC-5 (Washington, D.C.)", ZoneOffset.ofHours(-5)),
    MOSCOW("UTC+3 (Moscow)", ZoneOffset.ofHours(3)),
    BEIJING("UTC+8 (Beijing)", ZoneOffset.ofHours(8)),
    TOKYO("UTC+9 (Tokyo)", ZoneOffset.ofHours(9));

    private String zoneName;
    private ZoneOffset zone;

    ZoneOption(String zoneName, ZoneOffset zone) {
        this.zoneName = zoneName;
        this.zone = zone;
    }

    public String getZoneName() {
        return zoneName;
    }

    public ZoneOffset getZone() {
        return zone;
    }
}