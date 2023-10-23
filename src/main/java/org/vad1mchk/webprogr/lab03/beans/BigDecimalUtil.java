package org.vad1mchk.webprogr.lab03.beans;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.math.BigDecimal;

@Named("bdUtilBean")
@ApplicationScoped
public class BigDecimalUtil {
    public BigDecimal valueOf(Long value) {
        return new BigDecimal(value);
    }
}
