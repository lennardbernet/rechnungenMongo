package com.projekt.mysql.api;

import com.projekt.mysql.model.Adress;
import com.projekt.mysql.model.StandingOrder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Fehler beim select der Daueraufträge", e
            );
        }
        return list;
    }

    @Transactional
    public List<StandingOrder> saveStandingOrder(StandingOrder standingOrder) {
        List<StandingOrder> list = null;
        try {
            em.persist(standingOrder.getAdress());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Fehler beim Hinzufügen der Adresse", e
            );
        }

        standingOrder.getAdress().setAdressId(em.find(Adress.class, standingOrder.getAdress().getAdressId()).getAdressId());
        try {
            em.persist(standingOrder);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Fehler beim Hinzufügen des Dauerauftrages", e
            );
        }
        list = getAllStandingOrders();
        return list;
    }

    @Transactional
    public void deleteById(int id) {
        StandingOrder matchedStandingOrder = em.find(StandingOrder.class, id);
        if (matchedStandingOrder == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Kein Dauerauftrag mit id " + id + "gefunden"
            );
        } else {
            try {
                em.remove(matchedStandingOrder);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                throw new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR, "Fehler beim Löschen des Dauerauftrages mit id " + id, e
                );
            }
        }

    }

    @Transactional
    public void executeStandingOrder(int id) {
        StandingOrder matchedStandingOrder;
        try {
            matchedStandingOrder = em.find(StandingOrder.class, id);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Dauerauftrag mit ID " + id + " nicht gefunden", e
            );
        }
        int executions = matchedStandingOrder.getExecutions();
        try {
            if (executions <= 1) {
                em.remove(matchedStandingOrder);
            } else {
                matchedStandingOrder.setExecutions(executions - 1);
                em.merge(matchedStandingOrder);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Fehler bei DB Zugriff", e
            );
        }
    }

}
