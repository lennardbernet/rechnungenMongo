package com.projekt.rechnungen.rest;

import com.projekt.rechnungen.api.StandingOrderService;
import com.projekt.rechnungen.model.StandingOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ok(standingOrderService.getAllStandingOrders());
    }

    @PostMapping(value = "saveStandingOrder",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StandingOrder>> saveStandingOrderWithoutAdress(@RequestBody StandingOrder standingOrder) {
        return ok(standingOrderService.saveStandingOrder(standingOrder));
    }

    @GetMapping(value = "deleteStandingOrder/{id}")
    public void deleteStandingOrderById(@PathVariable("id") Integer id) {
        standingOrderService.deleteById(id);
    }

    @GetMapping(value = "executeStandingOrder/{id}")
    public void executeStandingOrderById(@PathVariable("id") Integer id) {
        standingOrderService.executeStandingOrder(id);
    }

}
