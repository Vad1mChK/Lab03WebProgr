package org.vad1mchk.webprogr.lab03.beans;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@ManagedBean
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
        if (isSelectedXMinus3()) result.add(new BigDecimal("-3"));
        if (isSelectedXMinus2()) result.add(new BigDecimal("-2"));
        if (isSelectedXMinus1()) result.add(new BigDecimal("-1"));
        if (isSelectedX0()) result.add(BigDecimal.ZERO);
        if (isSelectedX1()) result.add(BigDecimal.ONE);
        if (isSelectedX2()) result.add(new BigDecimal("2"));
        if (isSelectedX3()) result.add(new BigDecimal("3"));
        if (isSelectedX4()) result.add(new BigDecimal("4"));
        if (isSelectedX5()) result.add(new BigDecimal("5"));
        return result;
    }
}
