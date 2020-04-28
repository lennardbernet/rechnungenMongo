package com.projekt.mongodb.rest;


import com.projekt.mongodb.api.BillService;
import com.projekt.mongodb.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "bill")
public class BillController {

    private BillService billService;

    @Autowired
    public BillController(BillService rechnungService) {
        this.billService = rechnungService;
    }

    public BillController() {

    }

    @GetMapping(value = "getAllBills")
    public ResponseEntity<List<Bill>> getAllBills() {
        try {
            List<Bill> list = billService.getAllBills();
            return ok(list);
        } catch (ResponseStatusException e) {
            throw e;
        }

    }

    @PostMapping(value = "saveBill",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Bill>> saveBillWithAdress(@RequestBody Bill bill) {
        try {
            List<Bill> bills = billService.saveBill(bill);
            return ok(bills);
        } catch (ResponseStatusException e) {
            throw e;
        }
    }

    @DeleteMapping(value = "deleteBill/{id}")
    public void deleteBillById(@PathVariable("id") String id) {
        try {
            billService.deleteBillById(id);
        }catch (ResponseStatusException e){
            throw e;
        }
    }
}
