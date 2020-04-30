package com.projekt.mongodb.api;


import com.projekt.mongodb.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class BillService {

    private BillRepository billRepository;

    @Autowired
    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public List<Bill> getAllBills() {
        try {
            return billRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error, response code 500", e
            );
        }
    }

    public List<Bill> findByFirstname(String firstname){
        try {
            return billRepository.findByFirstname(firstname);
        } catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error, response code 500", e
            );
        }
    }

    public List<Bill> saveBill(Bill bill) {
        try {
            billRepository.save(bill);
            return getAllBills();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error, response code 500", e
            );
        }
    }

    public void deleteBillById(String id) {
        try {
            billRepository.delete(billRepository.findById(id).get());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error, response code 500", e
            );
        }

    }

}
