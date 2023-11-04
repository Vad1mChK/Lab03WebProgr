package org.vad1mchk.webprogr.lab03.beans;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ShotHitChecker {
    public static boolean check(BigDecimal x, BigDecimal y, BigDecimal r) {
        int xSign = x.signum();
        int ySign = y.signum();

        return (ySign >= 0 && xSign >= 0 && checkTopRightQuadrant(x, y, r)) ||
                (ySign >= 0 && xSign <= 0 && checkTopLeftQuadrant(x, y, r)) ||
                (ySign <= 0 && xSign <= 0 && checkBottomLeftQuadrant(x, y, r)) ||
                (ySign <= 0 && xSign >= 0 && checkBottomRightQuadrant(x, y, r));
    }

    private static boolean checkTopRightQuadrant(BigDecimal x, BigDecimal y, BigDecimal r) {
        return false; // It's a FEATURE
    }

    private static boolean checkTopLeftQuadrant(BigDecimal x, BigDecimal y, BigDecimal r) {
        BigDecimal halfR = r.setScale(r.scale() + 1, RoundingMode.HALF_UP)
                .divide(new BigDecimal(2), RoundingMode.HALF_UP);
        return x.compareTo(halfR.negate()) >= 0 && y.compareTo(r) <= 0;
    }

    private static boolean checkBottomLeftQuadrant(BigDecimal x, BigDecimal y, BigDecimal r) {
        BigDecimal halfR = r.setScale(r.scale() + 1, RoundingMode.HALF_UP)
                .divide(new BigDecimal(2), RoundingMode.HALF_UP);
        return x.multiply(x).add(y.multiply(y)).compareTo(halfR.multiply(halfR)) <= 0;
    }

    private static boolean checkBottomRightQuadrant(BigDecimal x, BigDecimal y, BigDecimal r) {
        return x.subtract(y).compareTo(r) <= 0;
    }
}
