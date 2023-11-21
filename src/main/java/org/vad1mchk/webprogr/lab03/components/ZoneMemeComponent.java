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
 * <p>
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

    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        if (context == null) {
            throw new NullPointerException("FacesContext cannot be null");
        }

        ZoneOffset zone = (ZoneOffset) getAttributes().get("zone");

        System.out.println("Rendering meme...");
        ResponseWriter writer = context.getResponseWriter();
        writer.startElement("img", this);

        String clientId = getClientId(context);
        if (clientId != null && !clientId.isEmpty()) {
            writer.writeAttribute("id", clientId, "id");
        }

        String imageUrl = determineImageUrl(zone);
        writer.writeAttribute("src", imageUrl, null);

        writer.endElement("img");
    }

    @Override
    public String getFamily() {
        return "vad1mchk.components";
    }


    private String determineImageUrl(ZoneOffset zone) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String contextPath = facesContext.getExternalContext().getRequestContextPath();
        return contextPath + "/resources/img/memes/" + MEMES.getOrDefault(zone, DEFAULT_MEME);
    }
}