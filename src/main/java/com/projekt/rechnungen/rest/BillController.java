package com.projekt.rechnungen.rest;


import com.projekt.rechnungen.api.BillService;
import com.projekt.rechnungen.model.Adress;
import com.projekt.rechnungen.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
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

    @GetMapping(value = "getAllBills")
    public ResponseEntity<List<Bill>> getBills() {
        List<Bill> list = billService.getAllBills();
        return ok(list);
    }

    @PostMapping(value = "saveBill",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Bill>> saveBillWithAdress(@RequestBody Bill bill) {
        billService.saveBill(bill);
        return ok(billService.saveBill(bill));
    }


}
