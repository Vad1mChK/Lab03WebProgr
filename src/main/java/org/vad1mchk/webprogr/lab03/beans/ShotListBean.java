package org.vad1mchk.webprogr.lab03.beans;

import org.vad1mchk.webprogr.lab03.database.ShotDao;
import org.vad1mchk.webprogr.lab03.database.ShotDaoImplementation;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@SessionScoped
@ManagedBean
public class ShotListBean implements Serializable {
    @Inject
    private SelectXBean xBean;

    @Inject
    private InputYBean yBean;

    @Inject
    private SelectRBean rBean;

    @Inject
    private TimeZoneBean zoneBean;

    private ShotDao shotDao;

    private List<ShotBean> previousShots;
    private ShotBean lastShot;

    public ShotListBean() {
        super();
        previousShots = new LinkedList<>();
        shotDao = new ShotDaoImplementation();
        loadShots();
    }

    public void loadShots() {
        previousShots = shotDao.getAllShots();
        if (!previousShots.isEmpty()) {
            lastShot = previousShots.get(previousShots.size() - 1);
        } else {
            lastShot = null;
        }
    }

    public void addShots() {
        List<ShotBean> shotsToSend = new ArrayList<>();

        BigDecimal y = yBean.getY();
        BigDecimal r = rBean.getR();
        ZoneOffset zone = zoneBean.getZone();

        List<BigDecimal> xs = xBean.getAllSelectedValues();
        if (xs == null || xs.isEmpty() || y == null || r == null || zone == null) {
            return;
        }

        for (BigDecimal x : xs) {
            if (x == null) continue;

            long startTime = System.nanoTime();

            ShotBean shot = new ShotBean();

            boolean hit = ShotHitChecker.check(x, y, r);
            ZonedDateTime creationDateTime = ZonedDateTime.now(zone);

            shot.setX(x);
            shot.setY(y);
            shot.setR(r);

            shot.setHit(hit);
            shot.setCreationDateTime(creationDateTime);

            long endTime = System.nanoTime();
            shot.setTimeElapsed(endTime - startTime);

            ShotBean addedShot = shotDao.insertShot(shot);
            if (addedShot != null) {
                shotsToSend.add(addedShot);
                lastShot = addedShot;
            }
        }
    }

    public void clearShots() {
        shotDao.deleteAllShots();
        previousShots.clear();
        lastShot = null;
    }

    @Named("previousShots")
    public List<ShotBean> getPreviousShots() {
        return previousShots;
    }

    public void setPreviousShots(List<ShotBean> previousShots) {
        this.previousShots = previousShots;
    }

    public ShotBean getLastShot() {
        return lastShot;
    }

    public void setLastShot(ShotBean lastShot) {
        this.lastShot = lastShot;
    }
}
