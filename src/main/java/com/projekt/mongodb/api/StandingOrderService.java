package com.projekt.mongodb.api;

import com.projekt.mongodb.model.StandingOrder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StandingOrderService {

    private StandingOrderRepository standingOrderRepository;

    @Autowired
    public StandingOrderService(StandingOrderRepository standingOrderRepository) {
        this.standingOrderRepository = standingOrderRepository;
    }

    public List<StandingOrder> getAllStandingOrders(){
        return standingOrderRepository.findAll();
    }

    public List<StandingOrder> saveStandingOrder(StandingOrder standingOrder){
        standingOrderRepository.save(standingOrder);
        return getAllStandingOrders();
    }

    public void deleteById(String id){
        standingOrderRepository.deleteById(id);
    }

    public void executeStandingOrder(String id){
        StandingOrder standingOrder = standingOrderRepository.findById(id).get();
        int executions = standingOrder.getExecutinos();
        if(executions <= 1 ){
            deleteById(id);
        }else{
            standingOrder.setExecutinos(executions-1);
            standingOrderRepository.save(standingOrder);
        }
    }

}
