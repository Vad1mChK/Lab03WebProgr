package org.vad1mchk.webprogr.lab03.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Entity
public class Shot {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpaSequence")
    @SequenceGenerator(name = "jpaSequence", sequenceName = "JPA_SEQUENCE", allocationSize = 1)
    private int id;
    private BigDecimal x;
    private BigDecimal y;
    private BigDecimal r;
    private boolean hit;
    private ZonedDateTime creationDatetime;

    {
        if (r.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Radius cannot be less than 0.");
        }
    }

    public Shot() {
        this.x = BigDecimal.ZERO;
        this.y = BigDecimal.ZERO;
        this.r = BigDecimal.ONE;
        this.hit = true;
        this.creationDatetime = ZonedDateTime.now();
    }

    public Shot(BigDecimal x, BigDecimal y, BigDecimal r, ZoneOffset zone) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.hit = checkShot(x, y, r);
        this.creationDatetime = ZonedDateTime.now(zone);
    }

    private boolean checkShot(BigDecimal x, BigDecimal y, BigDecimal r) {
        int xSign = x.compareTo(BigDecimal.ZERO);
        int ySign = y.compareTo(BigDecimal.ZERO);
        BigDecimal two = BigDecimal.ONE.add(BigDecimal.ONE);
        BigDecimal halfR = r.divide(two, RoundingMode.HALF_DOWN);
        // Assuming the edge points belong to the area onwards
        if (xSign <= 0 && ySign <= 0) { // 3rd quadrant (circle with radius r/2)
            return (x.pow(2)).add(y.pow(2)).compareTo(halfR.pow(2)) <= 0;
        } else if (xSign <= 0) { // 2nd quadrant (rectangle)
            return x.compareTo(halfR.negate()) >= 0 && y.compareTo(r) <= 0;
        } else if (ySign <= 0) { // 4th quadrant (triangle)
            return x.subtract(y).compareTo(r) <= 0;
        } else { // 1st quadrant (nothing)
            return false;
        }
    }
}
