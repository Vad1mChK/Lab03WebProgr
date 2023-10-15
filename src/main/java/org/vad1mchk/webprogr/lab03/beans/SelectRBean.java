package org.vad1mchk.webprogr.lab03.beans;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;

@Named("rBean")
@SessionScoped
@ManagedBean
public class SelectRBean implements Serializable {
    private RChoice rChoice;
    private static final BigDecimal R_MIN = BigDecimal.ONE;
    private static final BigDecimal R_MAX = new BigDecimal(5);

    public enum RChoice {
        R_1(BigDecimal.ONE),
        R_2(new BigDecimal(2)),
        R_3(new BigDecimal(3)),
        R_4(new BigDecimal(4)),
        R_5(new BigDecimal(5));
        private final BigDecimal value;

        RChoice(BigDecimal value) {
            this.value = value;
        }

        public BigDecimal getValue() {
            return value;
        }
    }

    public BigDecimal getValue() {
        return (rChoice != null) ? rChoice.getValue() : null;
    }

    public void setValue(BigDecimal value) {
        if (
                Arrays.stream(RChoice.values())
                        .map(RChoice::getValue)
                        .noneMatch((it) -> it.equals(value))
        ) {
            throw new IllegalArgumentException(
                    "R value should be one of the values " +
                            Arrays.toString(Arrays.stream(RChoice.values()).map(RChoice::getValue).toArray()) +
                            ", " + value + " given"
                    );
        }
        this.rChoice = Arrays.stream(RChoice.values())
                .filter((it) -> it.getValue().equals(value))
                .findFirst()
                .orElse(null);
    }

    public RChoice getrChoice() {
        return rChoice;
    }

    public void setrChoice(RChoice rChoice) {
        this.rChoice = rChoice;
    }

    public RChoice[] getrChoiceValues() {
        return RChoice.values();
    }
}
// boy do I want to use Kotlin instead
