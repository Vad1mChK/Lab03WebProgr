package org.vad1mchk.webprogr.lab03.beans;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * A class for checking if a shot hits a target based on its coordinates and radius.
 *
 * @author Vadim Chkinev
 */
public class ShotHitChecker {
    /**
     * Checks if a shot hits the target based on its coordinates and radius.
     *
     * @param x the x-coordinate of the shot
     * @param y the y-coordinate of the shot
     * @param r the radius of the target
     * @return true if the shot hits the target, false otherwise
     */
    public static boolean check(BigDecimal x, BigDecimal y, BigDecimal r) {
        int xSign = x.signum();
        int ySign = y.signum();

        return (ySign >= 0 && xSign >= 0 && checkTopRightQuadrant(x, y, r)) ||
                (ySign >= 0 && xSign <= 0 && checkTopLeftQuadrant(x, y, r)) ||
                (ySign <= 0 && xSign <= 0 && checkBottomLeftQuadrant(x, y, r)) ||
                (ySign <= 0 && xSign >= 0 && checkBottomRightQuadrant(x, y, r));
    }

    /**
     * Checks if the shot hits the top right quadrant of the target.
     *
     * @param x the x-coordinate of the shot
     * @param y the y-coordinate of the shot
     * @param r the radius of the target
     * @return true if the shot hits the top right quadrant, false otherwise
     */
    private static boolean checkTopRightQuadrant(BigDecimal x, BigDecimal y, BigDecimal r) {
        return false; // The area does not cover the top right quadrant
    }

    /**
     * Checks if the shot hits the top left quadrant of the target.
     *
     * @param x the x-coordinate of the shot
     * @param y the y-coordinate of the shot
     * @param r the radius of the target
     * @return true if the shot hits the top left quadrant, false otherwise
     */
    private static boolean checkTopLeftQuadrant(BigDecimal x, BigDecimal y, BigDecimal r) {
        BigDecimal halfR = r.setScale(r.scale() + 1, RoundingMode.HALF_UP)
                .divide(new BigDecimal(2), RoundingMode.HALF_UP);
        return x.compareTo(halfR.negate()) >= 0 && y.compareTo(r) <= 0;
    }

    /**
     * Checks if the shot hits the bottom left quadrant of the target.
     *
     * @param x the x-coordinate of the shot
     * @param y the y-coordinate of the shot
     * @param r the radius of the target
     * @return true if the shot hits the bottom left quadrant, false otherwise
     */
    private static boolean checkBottomLeftQuadrant(BigDecimal x, BigDecimal y, BigDecimal r) {
        BigDecimal halfR = r.setScale(r.scale() + 1, RoundingMode.HALF_UP)
                .divide(new BigDecimal(2), RoundingMode.HALF_UP);
        return x.multiply(x).add(y.multiply(y)).compareTo(halfR.multiply(halfR)) <= 0;
    }

    /**
     * Checks if the shot hits the bottom right quadrant of the target.
     *
     * @param x the x-coordinate of the shot
     * @param y the y-coordinate of the shot
     * @param r the radius of the target
     * @return true if the shot hits the bottom right quadrant, false otherwise
     */
    private static boolean checkBottomRightQuadrant(BigDecimal x, BigDecimal y, BigDecimal r) {
        return x.subtract(y).compareTo(r) <= 0;
    }
}
