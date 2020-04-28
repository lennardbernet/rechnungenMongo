package com.projekt.mongodb.api;

import com.projekt.mongodb.model.StandingOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StandingOrderService {

    private StandingOrderRepository standingOrderRepository;

    @Autowired
    public StandingOrderService(StandingOrderRepository standingOrderRepository) {
        this.standingOrderRepository = standingOrderRepository;
    }

    public List<StandingOrder> getAllStandingOrders(){
        try {
            return standingOrderRepository.findAll();
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,"Find all standing orders failed",e
            );
        }
    }

    public List<StandingOrder> saveStandingOrder(StandingOrder standingOrder){
        try {
            standingOrderRepository.save(standingOrder);
            return getAllStandingOrders();
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,"Save standing order failed",e
            );
        }
    }

    public void deleteById(String id){
        try {
            standingOrderRepository.deleteById(id);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Delete standing order with id "+id+" failed",e
            );
        }
    }

    public void executeStandingOrder(String id){
        try {
            StandingOrder standingOrder = standingOrderRepository.findById(id).get();
            int executions = standingOrder.getExecutinos();
            if (executions <= 1) {
                deleteById(id);
            } else {
                standingOrder.setExecutinos(executions - 1);
                standingOrderRepository.save(standingOrder);
            }
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Update standing order failed", e
            );
        }
    }

}
