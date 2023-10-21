package org.vad1mchk.webprogr.lab03.beans;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ShotHitChecker {
    public static boolean check(BigDecimal x, BigDecimal y, BigDecimal r) {
        BigDecimal two = new BigDecimal(2);
        BigDecimal halfR = r.divide(two, r.scale() + 1, RoundingMode.HALF_UP);
        int xSign = x.signum();
        int ySign = y.signum();

        if (ySign >= 0) {
            if (xSign >= 0) { // 1st quadrant
                return false;
            } else {
                return x.compareTo(halfR.negate()) >= 0 && y.compareTo(r) <= 0;
            }
        } else {
            if (xSign <= 0) {
                return x.subtract(y).compareTo(r) <= 0;
            } else {
                return x.pow(2).add(y.pow(2)).compareTo(halfR.pow(2)) <= 0;
            }
        }
    }
}
