package org.vad1mchk.webprogr.lab03.components;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import java.io.IOException;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Objects;

/**
 * Component for displaying a meme as an image based on the underlying {@code ZoneOffset} which can be changed
 * dynamically.
 *
 * Outputs a fitting meme image using the HTML {@code <img ... />} tag, with a default placeholder image in case of an
 * invalid timezone.
 *
 * @author Vad1mChK
 */
@FacesComponent(createTag = true, tagName = "zoneMeme", namespace = "vad1mchk")
public class ZoneMemeComponent extends UIComponentBase {
    @Override
    public String getFamily() {
        return "zoneMeme";
    }

    public enum AvailableZone {
        WASHINGTON_DC(ZoneOffset.ofHours(-5), "Washington, D.C.", "washingtonDc.jpg"),
        MOSCOW(ZoneOffset.ofHours(3), "Moscow | Москва","moscow.jpg"),
        BEIJING(ZoneOffset.ofHours(8), "Beijing | 北京", "beijing.png"),
        TOKYO(ZoneOffset.ofHours(9), "Tokyo | 東京", "tokyo.png");

        private final ZoneOffset offset;
        private final String zoneName;
        private final String memeName;

        AvailableZone(ZoneOffset zone, String zoneName, String memeName) {
            this.offset = zone;
            this.zoneName = zoneName;
            this.memeName = memeName;
        }

        public ZoneOffset getOffset() {
            return offset;
        }

        public String getMemeName() {
            return memeName;
        }
    }

    private AvailableZone zone;

    // Override necessary methods and add your logic for rendering the component
    // ...

    public void setZoneOffset(ZoneOffset zoneOffset) {
        zone = zoneOffset != null ?
                Arrays.stream(AvailableZone.values())
                        .filter(it -> Objects.equals(zoneOffset, it.getOffset()))
                        .findFirst()
                        .orElse(null) :
                null;
    }

    public ZoneOffset getZoneOffset() {
        return zone.getOffset();
    }

    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        if (context == null) {
            throw new NullPointerException("Context cannot be null for the ZoneMemeComponent");
        }
        ResponseWriter writer = context.getResponseWriter();
        writer.startElement("img", this);
        writer.writeAttribute("src", getResourcePath(determineMeme()), "src");
        writer.endElement("img");
    }

    private String getResourcePath(String imageName) {
        return "/resources/images/" + imageName;
    }

    private String determineMeme() {
        return zone != null ? zone.getMemeName() : "nullMeme.jpg";
    }
}