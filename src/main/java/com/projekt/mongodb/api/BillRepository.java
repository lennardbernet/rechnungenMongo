package com.projekt.mongodb.api;


import com.projekt.mongodb.model.Bill;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends MongoRepository<Bill, String> {

}
