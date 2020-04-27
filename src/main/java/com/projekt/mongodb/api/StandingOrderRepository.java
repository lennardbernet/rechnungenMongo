package com.projekt.mongodb.api;

import com.projekt.mongodb.model.StandingOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StandingOrderRepository extends MongoRepository<StandingOrder,String> {
}
