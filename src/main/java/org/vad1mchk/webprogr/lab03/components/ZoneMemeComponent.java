package org.vad1mchk.webprogr.lab03.components;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import java.io.IOException;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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

    private final Map<ZoneOffset, String> zoneMemeMap;

    private ZoneOffset zone;

    // Override necessary methods and add your logic for rendering the component
    // ...

    public ZoneMemeComponent() {
        zoneMemeMap = new HashMap<>() {{
            put(ZoneOffset.ofHours(-5), "washingtonDc.jpg");
            put(ZoneOffset.ofHours(3), "moscow.jpg");
            put(ZoneOffset.ofHours(8), "beijing.png");
            put(ZoneOffset.ofHours(9), "tokyo.png");
        }};
    }

    public void setZone(ZoneOffset zone) {
        this.zone = zone;
    }

    public ZoneOffset getZone() {
        return zone;
    }

    @Override
    public String getFamily() {
        return "zoneMeme";
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
        return zoneMemeMap.getOrDefault(zone, "nullMeme.jpg");
    }
}