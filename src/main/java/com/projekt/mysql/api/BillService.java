package com.projekt.mysql.api;

import com.projekt.mysql.model.Adress;
import com.projekt.mysql.model.Bill;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
            list = em.createNamedQuery("bill.getAllBills", Bill.class).getResultList();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Fehler beim select der Rechnung", e
            );
        }
        return list;
    }

    @Transactional
    public List<Bill> saveBill(Bill bill) {
        List<Bill> list = null;
        try {
            em.persist(bill.getAdress());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Fehler beim Hinzufügen der Adresse", e
            );
        }
        bill.getAdress().setAdressId(em.find(Adress.class, bill.getAdress().getAdressId()).getAdressId());
        try {
            em.persist(bill);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Fehler beim Hinzufügen der Rechnung", e
            );
        }
        list = getAllBills();
        return list;

    }

    @Transactional
    public void deleteById(int id) {
        Bill matchedBill = null;
        matchedBill = em.find(Bill.class, id);
        if (matchedBill == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Keine Rechnung mit id " + id + " gefunden"
            );
        } else {
            try {
                matchedBill.setBillId(6);
                em.remove(matchedBill);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                throw new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR, "Fehler beim löschen der Rechnung mit id: " + id, e
                );
            }
        }
    }


}
