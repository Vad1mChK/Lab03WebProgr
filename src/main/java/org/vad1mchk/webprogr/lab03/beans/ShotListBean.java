package org.vad1mchk.webprogr.lab03.beans;

import org.vad1mchk.webprogr.lab03.database.ShotDao;
import org.vad1mchk.webprogr.lab03.database.ShotDaoImplementation;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;

@SessionScoped
@Named
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

    @Named("previousShots")
    private List<ShotBean> previousShots;
    private ShotBean lastShot;

    public ShotListBean() {
        super();
        previousShots = new LinkedList<>();
        shotDao = new ShotDaoImplementation();
        loadShots();
    }

    public void loadShots() {
        List<ShotBean> shotsToLoad = shotDao.getAllShots();
        if (previousShots == null) {
            return;
        }
        previousShots = shotsToLoad;
        if (!previousShots.isEmpty()) {
            lastShot = previousShots.get(previousShots.size() - 1);
            previousShots.forEach((shot) -> {
                if (shot != null)
                    FacesContext.getCurrentInstance().getPartialViewContext().getEvalScripts().add(
                            "drawShot(" + shot.getX() + ", " + shot.getY() + ", " + shot.getR() + ")"
                    );
            });
        } else {
            lastShot = null;
        }
    }

    public void addShot(BigDecimal x, BigDecimal y, BigDecimal r) {
        ZoneOffset zone = zoneBean.getZone();

        if (x == null || y == null || r == null || zone == null) {
            return;
        }

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
        System.out.println("New shot: " + shot);
        if (addedShot != null) {
            previousShots.add(addedShot);
            lastShot = addedShot;
            FacesContext.getCurrentInstance().getPartialViewContext().getEvalScripts().add(
                    "drawShot(" + shot.getX() + ", " + shot.getY() + ", " + shot.getR() + ")"
            );
        }
    }

    public void addShots() {
        BigDecimal y = yBean.getY();
        BigDecimal r = rBean.getR();
        List<BigDecimal> xs = xBean.getAllSelectedValues();

        if (xs == null || xs.isEmpty()) {
            return;
        }

        for (BigDecimal x : xs) {
            addShot(x, y, r);
        }
    }

    public void clearShots() {
        shotDao.deleteAllShots();
        previousShots.clear();
        lastShot = null;
        System.out.println("Cleared all shots");
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
