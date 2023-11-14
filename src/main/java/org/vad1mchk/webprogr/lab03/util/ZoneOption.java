package org.vad1mchk.webprogr.lab03.util;

import java.time.ZoneOffset;

public enum ZoneOption {
    WASHINGTON("Вашингтон", ZoneOffset.ofHours(-5)),
    MOSCOW("Москва", ZoneOffset.ofHours(3)),
    BEIJING("Пекин", ZoneOffset.ofHours(8)),
    TOKYO("Токио", ZoneOffset.ofHours(9));

    private String zoneName;
    private ZoneOffset zoneOffset;

    ZoneOption(String zoneName, ZoneOffset zoneOffset) {
        this.zoneName = zoneName;
        this.zoneOffset = zoneOffset;
    }

    public String getZoneName() {
        return zoneName;
    }

    public ZoneOffset getZoneOffset() {
        return zoneOffset;
    }
}
