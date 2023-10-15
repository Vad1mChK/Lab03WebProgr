package org.vad1mchk.webprogr.lab03.beans;

import org.vad1mchk.webprogr.lab03.entities.Shot;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Named("shotBean")
@SessionScoped
@ManagedBean
public class ShotBean implements Serializable {
    private static final String PERSISTENCE_UNIT = "StudsPU";

    private Shot shot;
    private List<Shot> storedShots;

    private EntityManagerFactory managerFactory;
    private EntityManager manager;
    private EntityTransaction transaction;

    public ShotBean() {
        this.shot = new Shot();
        this.storedShots = new ArrayList<>();
    }

    private void connect() {
        managerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        manager = managerFactory.createEntityManager();
        transaction = manager.getTransaction();
    }

    public void loadShots() {
        try {
            transaction.begin();
            Query query = manager.createQuery("SELECT s FROM Shot s");
            storedShots = query.getResultList();
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public void addShot() {
        try {
            transaction.begin();
            manager.persist(shot);
            storedShots.add(shot);
            shot = new Shot();
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public void addShotWithParameters() {
        if (shot == null) shot = new Shot();
        try {
            Map<String, String> parameters = FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .getRequestParameterMap();
            transaction.begin();
            shot.setX(new BigDecimal(parameters.get("x")));
            shot.setY(new BigDecimal(parameters.get("y")));
            shot.setR(new BigDecimal(parameters.get("r")));
            shot.updateHit();
            manager.persist(shot);
            storedShots.add(shot);
            shot = new Shot();
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public String clearShots() {
        try {
            transaction.begin();
            Query query = manager.createQuery("DELETE FROM Shot");
            query.executeUpdate();
            storedShots.clear();
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
        return "redirect";
    }

    public Shot getShot() {
        return shot;
    }

    public void setShot(Shot shot) {
        this.shot = shot;
    }

    public List<Shot> getStoredShots() {
        return storedShots;
    }

    public void setStoredShots(List<Shot> storedShots) {
        this.storedShots = storedShots;
    }
}
