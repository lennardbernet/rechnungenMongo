package com.projekt.rechnungen.api;

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

    public List<StandingOrder> getAllStandingOrders(){
        List<StandingOrder> list = null;
        try{
            list = em.createQuery("select d from StandingOrder d", StandingOrder.class).getResultList();
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        return list;
    }

    @Transactional
    public List<StandingOrder> saveStandingOrder(StandingOrder standingOrder){
        List<StandingOrder> list = null;
        try{
            System.out.println(standingOrder.toString());
            list = getAllStandingOrders();
            em.persist(standingOrder);
            em.createNativeQuery("insert into StandingOrder (adress_idfk, amount,executions,firstname, iban, repetitionrate, lastname) values (?,?,?,?,?,?,?)")
                    .setParameter(1, standingOrder.getAdress().getAdressId())
                    .setParameter(2, standingOrder.getAmount())
                    .setParameter(3,standingOrder.getExecutions())
                    .setParameter(4, standingOrder.getFirstname())
                    .setParameter(5,standingOrder.getIban())
                    .setParameter(6,standingOrder.getRepetitionrate())
                    .setParameter(7,standingOrder.getLastname())
                    .executeUpdate();
            list = getAllStandingOrders();
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        return list;
    }

}
