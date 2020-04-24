package com.projekt.rechnungen.rest;

import com.projekt.rechnungen.api.StandingOrderService;
import com.projekt.rechnungen.model.Bill;
import com.projekt.rechnungen.model.StandingOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "standingOrder")
public class StandingOrderController {

    private StandingOrderService standingOrderService;

    @Autowired
    public StandingOrderController(StandingOrderService standingOrderService) {
        this.standingOrderService = standingOrderService;
    }

    @GetMapping(value = "getAllStandingOrders")
    public ResponseEntity<List<StandingOrder>> getDauerauftragAll() {
        try {
            List<StandingOrder> standingOrders = standingOrderService.getAllStandingOrders();
            return ok(standingOrders);
        } catch (ResponseStatusException e) {
            throw e;
        }

    }

    @PostMapping(value = "saveStandingOrder",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StandingOrder>> saveStandingOrderWithoutAdress(@RequestBody StandingOrder standingOrder) {
        try {
            List<StandingOrder> standingOrders = standingOrderService.saveStandingOrder(standingOrder);
            return ok(standingOrders);
        } catch (ResponseStatusException e) {
            throw e;
        }
    }

    @GetMapping(value = "deleteStandingOrder/{id}")
    public void deleteStandingOrderById(@PathVariable("id") Integer id) {
        try {
            standingOrderService.deleteById(id);
        } catch (ResponseStatusException e) {
            throw e;
        }
    }

    @GetMapping(value = "executeStandingOrder/{id}")
    public void executeStandingOrderById(@PathVariable("id") Integer id) {
        try {
            standingOrderService.executeStandingOrder(id);
        } catch (ResponseStatusException e) {
            throw e;
        }
    }

}
