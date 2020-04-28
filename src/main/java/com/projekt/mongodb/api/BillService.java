package com.projekt.mongodb.api;


import com.projekt.mysql.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Find All bills failed", e
            );
        }
    }

    public List<Bill> saveBill(Bill bill) {
        try {
            billRepository.save(bill);
            return getAllBills();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Save bill failed", e
            );
        }
    }

    public void deleteBillById(String id) {
        try {
            billRepository.delete(billRepository.findById(id).get());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Delete bill with id " + id + " failed", e
            );
        }

    }

}
