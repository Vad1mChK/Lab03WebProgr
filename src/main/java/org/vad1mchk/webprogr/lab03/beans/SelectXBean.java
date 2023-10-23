package org.vad1mchk.webprogr.lab03.beans;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@Named("xBean")
public class SelectXBean implements Serializable {
    private boolean selectedXMinus3;
    private boolean selectedXMinus2;
    private boolean selectedXMinus1;
    private boolean selectedX0;
    private boolean selectedX1;
    private boolean selectedX2;
    private boolean selectedX3;
    private boolean selectedX4;
    private boolean selectedX5;

    public SelectXBean() {
        super();
    }

    public boolean isSelectedXMinus3() {
        return selectedXMinus3;
    }

    public void setSelectedXMinus3(boolean selectedXMinus3) {
        this.selectedXMinus3 = selectedXMinus3;
    }

    public boolean isSelectedXMinus2() {
        return selectedXMinus2;
    }

    public void setSelectedXMinus2(boolean selectedXMinus2) {
        this.selectedXMinus2 = selectedXMinus2;
    }

    public boolean isSelectedXMinus1() {
        return selectedXMinus1;
    }

    public void setSelectedXMinus1(boolean selectedXMinus1) {
        this.selectedXMinus1 = selectedXMinus1;
    }

    public boolean isSelectedX0() {
        return selectedX0;
    }

    public void setSelectedX0(boolean selectedX0) {
        this.selectedX0 = selectedX0;
    }

    public boolean isSelectedX1() {
        return selectedX1;
    }

    public void setSelectedX1(boolean selectedX1) {
        this.selectedX1 = selectedX1;
    }

    public boolean isSelectedX2() {
        return selectedX2;
    }

    public void setSelectedX2(boolean selectedX2) {
        this.selectedX2 = selectedX2;
    }

    public boolean isSelectedX3() {
        return selectedX3;
    }

    public void setSelectedX3(boolean selectedX3) {
        this.selectedX3 = selectedX3;
    }

    public boolean isSelectedX4() {
        return selectedX4;
    }

    public void setSelectedX4(boolean selectedX4) {
        this.selectedX4 = selectedX4;
    }

    public boolean isSelectedX5() {
        return selectedX5;
    }

    public void setSelectedX5(boolean selectedX5) {
        this.selectedX5 = selectedX5;
    }

    public List<BigDecimal> getAllSelectedValues() {
        List<BigDecimal> result = new ArrayList<>(9);
        if (selectedXMinus3) result.add(new BigDecimal("-3"));
        if (selectedXMinus2) result.add(new BigDecimal("-2"));
        if (selectedXMinus1) result.add(new BigDecimal("-1"));
        if (selectedX0) result.add(BigDecimal.ZERO);
        if (selectedX1) result.add(BigDecimal.ONE);
        if (selectedX2) result.add(new BigDecimal("2"));
        if (selectedX3) result.add(new BigDecimal("3"));
        if (selectedX4) result.add(new BigDecimal("4"));
        if (selectedX5) result.add(new BigDecimal("5"));
        return result;
    }

    public void setAllSelectedValues(List<BigDecimal> values) {
        if (values == null) return;
        selectedXMinus3 = values.contains(new BigDecimal("-3"));
        selectedXMinus2 = values.contains(new BigDecimal("-2"));
        selectedXMinus1 = values.contains(new BigDecimal("-1"));
        selectedX0 = values.contains(BigDecimal.ZERO);
        selectedX1 = values.contains(BigDecimal.ONE);
        selectedX2 = values.contains(new BigDecimal("2"));
        selectedX3 = values.contains(new BigDecimal("3"));
        selectedX4 = values.contains(new BigDecimal("4"));
        selectedX5 = values.contains(new BigDecimal("5"));
    }

    public void validateX(FacesContext context, UIComponent component, Object o) {
        if (
                !selectedXMinus3 && !selectedXMinus2 && !selectedXMinus1 && !selectedX0 && !selectedX1 &&
                        !selectedX2 && !selectedX3 && !selectedX4 && !selectedX5
        ) {
            FacesMessage message = new FacesMessage("Выберите хотя бы одно значение X.");
            throw new ValidatorException(message);
        }
    }

    public void update() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "SelectXBean {" + getAllSelectedValues() + "}";
    }
}
