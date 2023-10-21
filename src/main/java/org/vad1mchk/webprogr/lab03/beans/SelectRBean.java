package org.vad1mchk.webprogr.lab03.beans;

import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.ValidationException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ViewScoped
@ManagedBean
@Named("rBean")
public class SelectRBean implements Serializable {
    private static final BigDecimal R_MIN = BigDecimal.ONE;
    private static final BigDecimal R_MAX = new BigDecimal(5);

    private List<BigDecimal> rValues;

    private BigDecimal value;

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
            value = null;
            FacesContext.getCurrentInstance().getPartialViewContext().getEvalScripts()
                    .add("cleanCanvas(getCanvas());");
            FacesMessage message = new FacesMessage("Необходимо выбрать значение R.");
            throw new ValidatorException(message);
        }
    }

    public List<BigDecimal> getrValues() {
        return rValues;
    }
}
