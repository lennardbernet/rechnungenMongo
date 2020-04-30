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
            List<StandingOrder> standingOrderList = standingOrderRepository.findAll();
            return standingOrderList;
        }catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error, response code 500", e
            );
        }
    }

    public List<StandingOrder> saveStandingOrder(StandingOrder standingOrder){
        try {
            standingOrderRepository.save(standingOrder);
            return getAllStandingOrders();
        }catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error, response code 500", e
            );
        }
    }

    public void deleteById(String id){
        try {
            standingOrderRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error, response code 500", e
            );
        }
    }

    public void executeStandingOrder(String id){
        try {
            StandingOrder standingOrder = standingOrderRepository.findById(id).get();
            int executions = standingOrder.getExecutions();
            if (executions <= 1) {
                deleteById(id);
            } else {
                standingOrder.setExecutions(executions - 1);
                standingOrderRepository.save(standingOrder);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error, response code 500", e
            );
        }
    }

}
