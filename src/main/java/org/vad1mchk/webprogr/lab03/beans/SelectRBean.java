package org.vad1mchk.webprogr.lab03.beans;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SessionScoped
@Named("rBean")
public class SelectRBean implements Serializable {
    private static final BigDecimal R_MIN = BigDecimal.ONE;
    private static final BigDecimal R_MAX = new BigDecimal(5);

    private final List<BigDecimal> rValues;

    private BigDecimal r;

    public SelectRBean() {
        super();
        List<BigDecimal> rValuesTemporary = new ArrayList<>();
        for (BigDecimal i = R_MIN; i.compareTo(R_MAX) <= 0; i = i.add(BigDecimal.ONE)) {
            rValuesTemporary.add(i);
        }
        rValues = Collections.unmodifiableList(rValuesTemporary);
    }

    public void validateR(FacesContext context, UIComponent component, Object o) {
        if (o == null) {
            r = null;
            FacesContext.getCurrentInstance().getPartialViewContext().getEvalScripts()
                    .add("cleanCanvas(getCanvas());");
            FacesMessage message = new FacesMessage("Необходимо выбрать значение R.");
            throw new ValidatorException(message);
        }
    }

    // no hecking clue why the lowercase r according to the JavaBeans naming convention
    public List<BigDecimal> getrValues() {
        return rValues;
    }

    public BigDecimal getR() {
        return r;
    }

    public void setR(BigDecimal r) {
        this.r = r;
    }
}
