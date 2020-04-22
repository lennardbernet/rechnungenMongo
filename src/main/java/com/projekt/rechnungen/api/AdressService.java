package com.projekt.rechnungen.api;

import com.projekt.rechnungen.model.Adress;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class AdressService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void saveAdress(Adress adress){
        try{
            System.out.println("adress service: "+adress.getStreet());
            em.persist(adress);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
    }

    public void removeAdress(Adress adress){
        try {
            em.remove(adress);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
    }

    public void updateAdress(Adress adress){
        try{
            em.merge(adress);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
    }

    public List<Adress> getAdresses() {
        List<Adress> list = null;
        try {
            list = em.createQuery("select a from Adress a", Adress.class).getResultList();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return list;
    }

}
