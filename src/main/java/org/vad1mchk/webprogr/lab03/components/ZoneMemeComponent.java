package org.vad1mchk.webprogr.lab03.components;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import java.io.IOException;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

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
    public static final String DEFAULT_MEME = "nullMeme.jpg";
    public static final Map<ZoneOffset, String> MEMES;

    static {
        MEMES = Collections.unmodifiableMap(new LinkedHashMap<>() {{
            put(ZoneOffset.ofHours(-5), "washingtonDc.jpg");
            put(ZoneOffset.ofHours(3), "moscow.jpg");
            put(ZoneOffset.ofHours(8), "beijing.png");
            put(ZoneOffset.ofHours(9), "tokyo.png");
        }});
    }

    private ZoneOffset zone;

    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        if (context == null) {
            throw new NullPointerException("FacesContext cannot be null");
        }

        // Start encoding the component
        ResponseWriter writer = context.getResponseWriter();
        writer.startElement("img", this); // Start the <img> tag

        String imageUrl = determineImageUrl(); // Implement this method based on ZoneOffset
        writer.writeAttribute("src", imageUrl, null); // Set the src attribute for the image
        // Add other necessary attributes like style, alt text, etc.

        writer.endElement("img"); // End the <img> tag
    }

    @Override
    public String getFamily() {
        return "vad1mchk.components";
    }

    public ZoneOffset getZone() {
        return this.zone;
    }

    public void setZone(ZoneOffset zone) {
        this.zone = zone;
    }

    private String determineImageUrl() {
        return "/resources/img/" + MEMES.getOrDefault(zone, DEFAULT_MEME);
    }
}