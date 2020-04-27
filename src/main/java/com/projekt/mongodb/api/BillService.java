package com.projekt.mongodb.api;


import com.projekt.mysql.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

    private BillRepository billRepository;

    @Autowired
    public BillService(BillRepository billRepository){
        this.billRepository = billRepository;
    }

    public List<Bill> getAllBills(){
        return billRepository.findAll();
    }

    public List<Bill> saveBill(Bill bill){
        billRepository.save(bill);
        return billRepository.findAll();
    }

    public void deleteBillById(String id){
        billRepository.delete(billRepository.findById(id).get());
    }

}
