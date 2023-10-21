package org.vad1mchk.webprogr.lab03.database;

import org.vad1mchk.webprogr.lab03.beans.ShotBean;

import java.math.BigDecimal;
import java.util.List;

public interface ShotDao {
    List<ShotBean> getAllShots();

    ShotBean insertShot(ShotBean shot);

    boolean deleteAllShots();
}
