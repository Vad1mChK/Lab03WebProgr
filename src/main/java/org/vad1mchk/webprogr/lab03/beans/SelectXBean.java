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
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@SessionScoped
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

    public void validateX(FacesContext context, UIComponent component, Object o) {
        if (o == null) {
            FacesMessage message = new FacesMessage("При валидации значений X был передан null.");
            throw new ValidatorException(message);
        }

        if (!(o instanceof List)) {
            FacesMessage message = new FacesMessage(
                    "При валидации значений X был передан не список (класс объекта: "
                            + o.getClass().getName() + ")."
            );
            throw new ValidatorException(message);
        }

        List<?> list = (List<?>) o;
        if (list.isEmpty()) {
            FacesMessage message = new FacesMessage("Выберите хотя бы одно значение X.");
            throw new ValidatorException(message);
        }
    }

    public void update() {
        allSelectedValues.clear();
        xs.keySet().forEach((key) -> {
            if (xs.get(key)) {
                allSelectedValues.add(key);
            }
        });
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "SelectXBean {" + allSelectedValues + ", " + xs + "}";
    }
}
