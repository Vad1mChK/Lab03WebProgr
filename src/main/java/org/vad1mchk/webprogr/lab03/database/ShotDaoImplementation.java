package org.vad1mchk.webprogr.lab03.database;

import org.vad1mchk.webprogr.lab03.beans.ShotBean;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

public class ShotDaoImplementation implements ShotDao {
    private static final String PERSISTENCE_UNIT = "StudsPU";
    private EntityManagerFactory managerFactory;
    private EntityManager manager;
    private EntityTransaction transaction;

    public ShotDaoImplementation() {
        super();
        connect();
    }

    private void connect() {
        managerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        manager = managerFactory.createEntityManager();
        transaction = manager.getTransaction();
    }

    @Override
    public List<ShotBean> getAllShots() {
        List<ShotBean> shots = null;
        try {
            transaction.begin();
            Query query = manager.createQuery("SELECT s FROM ShotBean s");
            shots = query.getResultList();
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            shots = null;
        }
        return shots;
    }

    @Override
    public ShotBean insertShot(ShotBean shot) {
        ShotBean newShot = null;
        try {
            transaction.begin();
            manager.persist(shot);
            // Some code for querying a new shot with a generated ID goes here
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            newShot = null;
        }
        return newShot;
    }

    @Override
    public boolean deleteAllShots() {
        try {
            transaction.begin();
            Query query = manager.createQuery("DELETE FROM ShotBean s");
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        }
        return true;
    }
}
