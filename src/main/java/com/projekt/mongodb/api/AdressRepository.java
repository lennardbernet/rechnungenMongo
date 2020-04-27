package com.projekt.mongodb.api;

import com.projekt.mongodb.model.Adress;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdressRepository extends MongoRepository<Adress, String> {
}
