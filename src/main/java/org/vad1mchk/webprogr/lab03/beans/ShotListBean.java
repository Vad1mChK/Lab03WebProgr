package org.vad1mchk.webprogr.lab03.beans;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@SessionScoped
@ManagedBean
@Named("shotListBean")
public class ShotListBean implements Serializable {
    @Inject
    private SelectXBean xBean;

    @Inject
    private InputYBean yBean;

    @Inject
    private SelectRBean rBean;

    private List<ShotBean> previousShots;

    public ShotListBean() {
        super();
        previousShots = new LinkedList<>();
    }
}
