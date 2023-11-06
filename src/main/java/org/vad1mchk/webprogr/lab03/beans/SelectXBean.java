package org.vad1mchk.webprogr.lab03.beans;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@ApplicationScoped
@Named("xBean")
public class SelectXBean implements Serializable {

    private Map<BigDecimal, Boolean> xs;

    private List<BigDecimal> allSelectedValues;

    public SelectXBean() {
        super();
        xs = new TreeMap<>() {{
            int xMin = -3;
            int xMax = 5;
            for (int x = xMin; x <= xMax; ++x) {
                put(new BigDecimal(x), false);
            }
        }};
        allSelectedValues = new ArrayList<>();
    }

    public Map<BigDecimal, Boolean> getXs() {
        return xs;
    }

    public void setXs(Map<BigDecimal, Boolean> xs) {
        this.xs = xs;
    }

    public List<BigDecimal> getAllSelectedValues() {
        return allSelectedValues;
    }

    public void setAllSelectedValues(List<BigDecimal> allSelectedValues) {
        this.allSelectedValues = allSelectedValues;
    }

    public void validateX(FacesContext context, UIComponent component, Object ignored) {
        update();
        if (allSelectedValues.isEmpty()) {
            FacesMessage message = new FacesMessage("Выберите хотя бы одно значение X.");
            throw new ValidatorException(message);
        }
    }

    public void update() {
        List<BigDecimal> updatedValues = new ArrayList<>();
        xs.keySet().forEach((key) -> {
            if (xs.get(key)) {
                updatedValues.add(key);
            }
        });
        allSelectedValues = updatedValues;
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "SelectXBean {" + allSelectedValues + ", " + xs + "}";
    }
}
