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
        if(standingOrder != null) {
            try {
                System.out.println(standingOrder.toString());
                em.persist(standingOrder);
                list = getAllStandingOrders();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }else{

        }
        return list;
    }

}
