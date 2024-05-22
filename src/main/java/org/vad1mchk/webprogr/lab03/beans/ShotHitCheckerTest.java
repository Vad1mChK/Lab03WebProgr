package org.vad1mchk.webprogr.lab03.beans;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ShotHitCheckerTest {
    @Test
    public void dotDroppedInCenter_shouldHitArea() {
        BigDecimal x = BigDecimal.ZERO;
        BigDecimal y = BigDecimal.ZERO;
        BigDecimal r = BigDecimal.TEN;
        assertTrue(ShotHitChecker.check(x, y, r));
    }

    @Test
    public void dotDroppedInTopRightCorner_shouldMissArea() {
        BigDecimal y = BigDecimal.ONE;
        BigDecimal r = BigDecimal.TEN;
        for (BigDecimal x = BigDecimal.ONE; x.compareTo(BigDecimal.TEN) <= 0; x = x.add(BigDecimal.ONE)) {
            assertFalse(ShotHitChecker.check(x, y, r));
        }
    }
}
