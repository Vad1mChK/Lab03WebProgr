package org.vad1mchk.webprogr.lab03.beans;

import org.vad1mchk.webprogr.lab03.converters.ZonedDateTimeAttributeConverter;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Table(name = "shot")
@SessionScoped
@ManagedBean
public class ShotBean implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "x")
    private BigDecimal x;

    @Column(name = "y")
    private BigDecimal y;

    @Column(name = "r")
    private BigDecimal r;

    @Column(name = "hit")
    private boolean hit;

    @Column(name = "creation_date_time")
    @Convert(converter = ZonedDateTimeAttributeConverter.class)
    private ZonedDateTime creationDateTime;

    @Column(name = "time_elapsed")
    private long timeElapsed;

    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "x")
    public BigDecimal getX() {
        return x;
    }

    public void setX(BigDecimal x) {
        this.x = x;
    }

    @Column(name = "y")
    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }

    @Column(name = "r")
    public BigDecimal getR() {
        return r;
    }

    public void setR(BigDecimal r) {
        this.r = r;
    }

    @Column(name = "hit")
    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    @Column(name = "creation_date_time")
    public ZonedDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(ZonedDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    @Column(name = "time_elapsed")
    public long getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(long timeElapsed) {
        this.timeElapsed = timeElapsed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShotBean)) return false;
        ShotBean shotBean = (ShotBean) o;
        return id == shotBean.id &&
                hit == shotBean.hit &&
                timeElapsed == shotBean.timeElapsed &&
                Objects.equals(x, shotBean.x) &&
                Objects.equals(y, shotBean.y) &&
                Objects.equals(r, shotBean.r) &&
                Objects.equals(creationDateTime, shotBean.creationDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, x, y, r, hit, creationDateTime, timeElapsed);
    }

    @Override
    public String toString() {
        return "{ id: " + id +
                ", x: \"" + x +
                "\", y: \"" + y +
                "\", r: \"" + r +
                "\", hit: " + hit +
                ", creationDateTime: \"" + creationDateTime +
                "\", timeElapsed: " + timeElapsed +
                '}';
    }
}
