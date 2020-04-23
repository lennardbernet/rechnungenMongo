package com.projekt.rechnungen.api;

import com.projekt.rechnungen.model.Adress;
import com.projekt.rechnungen.model.StandingOrder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class StandingOrderService {

    @PersistenceContext
    private EntityManager em;

    public List<StandingOrder> getAllStandingOrders() {
        List<StandingOrder> list = null;
        try {
            list = em.createQuery("select d from StandingOrder d", StandingOrder.class).getResultList();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Transactional
    public List<StandingOrder> saveStandingOrder(StandingOrder standingOrder) {
        List<StandingOrder> list = null;
        if (standingOrder != null) {
            try {
                em.persist(standingOrder.getAdress());
                standingOrder.getAdress().setAdressId(em.find(Adress.class, standingOrder.getAdress().getAdressId()).getAdressId());
                em.persist(standingOrder);
                list = getAllStandingOrders();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        } else {

        }
        return list;
    }

    @Transactional
    public void deleteById(int id) {
        StandingOrder matchedStandingOrder = em.find(StandingOrder.class, id);
        em.remove(matchedStandingOrder);
    }

    @Transactional
    public void executeStandingOrder(int id) {
        StandingOrder matchedStandingOrder = em.find(StandingOrder.class, id);
        int executions = matchedStandingOrder.getExecutions();
        if (executions <= 1) {
            em.remove(matchedStandingOrder);
        } else {
            matchedStandingOrder.setExecutions(executions - 1);
            em.merge(matchedStandingOrder);
        }
    }

}
