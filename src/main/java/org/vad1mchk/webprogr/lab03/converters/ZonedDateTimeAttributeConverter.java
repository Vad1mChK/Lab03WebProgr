package org.vad1mchk.webprogr.lab03.converters;

import org.vad1mchk.webprogr.lab03.beans.TimeZoneBean;

import javax.inject.Inject;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Converter(autoApply = true)
public class ZonedDateTimeAttributeConverter implements AttributeConverter<ZonedDateTime, Timestamp> {
    @Inject
    private TimeZoneBean zoneBean;

    private ZoneId zone() {
        if (zoneBean == null || zoneBean.getZone() == null) return ZoneOffset.systemDefault();
        return zoneBean.getZone();
    }

    @Override
    public Timestamp convertToDatabaseColumn(ZonedDateTime attribute) {
        System.out.println("Converting " + attribute + " to database column");
        if (attribute == null) return null;
        return Timestamp.from(attribute.toInstant());
    }

    @Override
    public ZonedDateTime convertToEntityAttribute(Timestamp dbData) {
        System.out.println("Converting " + dbData + " to entity attribute");
        if (dbData == null) return null;
        return dbData.toLocalDateTime().atZone(zone());
    }
}
