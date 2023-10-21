package org.vad1mchk.webprogr.lab03.beans;

import org.vad1mchk.webprogr.lab03.database.ShotDao;
import org.vad1mchk.webprogr.lab03.database.ShotDaoImplementation;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
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
}
