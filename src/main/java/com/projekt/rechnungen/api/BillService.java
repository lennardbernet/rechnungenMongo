package com.projekt.rechnungen.api;

import com.projekt.rechnungen.model.Adress;
import com.projekt.rechnungen.model.Bill;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class BillService {

    @PersistenceContext
    private EntityManager em;

    public List<Bill> getAllBills() {
        List<Bill> list = null;
        try {
            //list = em.createQuery("select r from Bill r", Bill.class).getResultList();
            list = em.createNamedQuery("bill.getAllBills",Bill.class).getResultList();
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        return list;
    }

    @Transactional
    public List<Bill> saveBill(Bill bill){
        List<Bill> list = null;
        try{
            em.persist(bill.getAdress());
            bill.getAdress().setAdressId(em.find(Adress.class, bill.getAdress().getAdressId()).getAdressId());
            em.persist(bill);
        list = getAllBills();
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        return list;
    }

    @Transactional
    public void deleteById(int id){
        Bill matchedBill = em.find(Bill.class, id);
        em.remove(matchedBill);
    }



}
