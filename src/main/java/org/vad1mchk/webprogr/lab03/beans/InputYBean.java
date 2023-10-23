package org.vad1mchk.webprogr.lab03.beans;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;

@SessionScoped
@Named("yBean")
public class InputYBean implements Serializable {
    private static final BigDecimal Y_MIN = new BigDecimal(-4);
    private static final BigDecimal Y_MAX = new BigDecimal(4);
    private BigDecimal y;

    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }

    public void validateY(FacesContext context, UIComponent component, Object o) {
        if (o == null) {
            y = null;
            FacesMessage message = new FacesMessage("Необходимо выбрать значение Y.");
            throw new ValidatorException(message);
        }
        if (!(o instanceof BigDecimal)) {
            y = null;
            FacesMessage message = new FacesMessage("Значение y должно быть числом.");
            throw new ValidatorException(message);
        }
        BigDecimal value = (BigDecimal) o;
        if (value.compareTo(Y_MIN) <= 0 || value.compareTo(Y_MAX) >= 0) {
            y = null;
            FacesMessage message = new FacesMessage("Значение y должно быть в диапазоне (" +
                    Y_MIN + ".." + Y_MAX + ").");
        }
    }
}
