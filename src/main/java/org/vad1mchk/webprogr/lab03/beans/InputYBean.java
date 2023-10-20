package org.vad1mchk.webprogr.lab03.beans;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;

@ViewScoped
@ManagedBean
@Named("yBean")
public class InputYBean implements Serializable {
    private static final BigDecimal Y_MIN = new BigDecimal(-4);
    private static final BigDecimal Y_MAX = new BigDecimal(4);
    private BigDecimal y;

    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        validate(y);
        this.y = y;
    }

    private void validate(BigDecimal value) {
        if (value == null) {
            throw new IllegalArgumentException(
                    "Y should not be null"
            );
        }
        if (value.compareTo(Y_MIN) <= 0 || value.compareTo(Y_MAX) >= 0) {
            throw new IllegalArgumentException(
                    "Y should be within the interval of (" + Y_MIN + ".." + Y_MAX + "), " + value + " given"
            );
        }
    }
}
