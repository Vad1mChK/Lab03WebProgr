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
@Named("shotListBean")
public class ShotListBean implements Serializable {
    private final ShotDao shotDao;
    @Inject
    private SelectXBean xBean;
    @Inject
    private InputYBean yBean;
    @Inject
    private SelectRBean rBean;
    @Inject
    private TimeZoneBean zoneBean;
    @Named("previousShots")
    private List<ShotBean> previousShots;
    private ShotBean lastShot;

    public ShotListBean() {
        super();
        previousShots = new LinkedList<>();
        shotDao = new ShotDaoImplementation();
    }

    public void loadShots() {
        System.out.println("Loading shots...");
        List<ShotBean> shotsToLoad = shotDao.getAllShots();
        if (shotsToLoad == null) {
            return;
        }
        previousShots = shotsToLoad;
        if (!previousShots.isEmpty()) {
            lastShot = previousShots.get(previousShots.size() - 1);
            previousShots.forEach((shot) -> {
                if (shot != null)
                    FacesContext.getCurrentInstance().getPartialViewContext().getEvalScripts().add(
                            "addShot(" + shot.getX() + ", " +
                                    shot.getY() + ", " +
                                    shot.getR() + ", " +
                                    shot.isHit() + ");"
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
                    "addShot(" + shot.getX() + ", " +
                            shot.getY() + ", " +
                            shot.getR() + ", " +
                            shot.isHit() + ");"
            );
        }
    }

    public void addShots() {
        System.out.println("Adding shots...");
        BigDecimal y = yBean.getY();
        System.out.println("Entered Y: " + y);
        BigDecimal r = rBean.getR();
        System.out.println("Selected R: " + r);
        xBean.update();
        List<BigDecimal> xs = xBean.getAllSelectedValues();
        System.out.println("All selected Xs: " + xs);

        if (xs == null || xs.isEmpty()) {
            return;
        }

        for (BigDecimal x : xs) {
            addShot(x, y, r);
        }
        System.out.println("Added " + xs.size() + " shots.");
    }

    public void clearShots() {
        System.out.println("Preparing to clear shots.");
        if (!shotDao.deleteAllShots()) {
            System.out.println("An error occurred when clearing the shots table in the database.");
        } else {
            System.out.println("Shots cleared in database");
        }
        previousShots.clear();
        lastShot = null;
        FacesContext.getCurrentInstance().getPartialViewContext().getEvalScripts().add("cleanAll();");
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
