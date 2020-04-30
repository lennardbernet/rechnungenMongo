package com.projekt.mongodb.rest;


import com.projekt.mongodb.api.BillService;
import com.projekt.mongodb.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.concurrent.ExecutionException;

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
            List<Bill> bills = billService.getAllBills();
            if (bills == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(bills);
            }
        } catch (Exception e) {
            throw e;
        }

    }

    @GetMapping(value = "findByFirstname/{firstname}")
    public ResponseEntity<List<Bill>> findByFirstname(@PathVariable("firstname") String firstname) {
        try {
            List<Bill> bills = billService.findByFirstname(firstname);
            if(bills == null){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(bills);
            }
        }catch (Exception e){
            throw e;
        }

    }

    @PostMapping(value = "saveBill",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Bill>> saveBill(@RequestBody Bill bill) {
        try {
            List<Bill> bills = billService.saveBill(bill);
            return ResponseEntity.status(HttpStatus.CREATED).body(bills);
        } catch (Exception e) {
            throw e;
        }
    }

    @DeleteMapping(value = "deleteBill/{id}")
    public ResponseEntity deleteBillById(@PathVariable("id") String id) {
        try {
            billService.deleteBillById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception e){
            throw e;
        }
    }
}
