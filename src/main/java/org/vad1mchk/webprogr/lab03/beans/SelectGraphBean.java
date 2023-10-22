package org.vad1mchk.webprogr.lab03.beans;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;

@SessionScoped
@Named("graphBean")
public class SelectGraphBean implements Serializable {
    private BigDecimal x;
    private BigDecimal y;

    public SelectGraphBean() {
        super();
    }

    public BigDecimal getX() {
        return x;
    }

    public BigDecimal getY() {
        return y;
    }

    public void setX(BigDecimal x) {
        this.x = x;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }
}